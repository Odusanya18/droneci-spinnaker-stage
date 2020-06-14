package com.github.odusanya18.droneci.gate.client.internal

import com.github.odusanya18.droneci.services.internal.IgorService
import com.netflix.spinnaker.gate.config.ServiceConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException
import kotlin.reflect.KClass

class ServiceClient(private val serviceConfiguration: ServiceConfiguration) {
    private val converter = GsonConverterFactory.create()
    private val okHttpClient = OkHttpClient()
    val igorService = createService("igor", IgorService::class)

    private fun <S : Any> createService(serviceName: String, serviceClass: KClass<S>, dynamicName :String? = null, forceEnabled: Boolean = false): S {
        val service = serviceConfiguration.getService(serviceName)
            ?: throw IllegalArgumentException("Unknown service $serviceName requested of type $serviceClass")

        if (!service.enabled && !forceEnabled) {
            throw IllegalStateException("Disabled service $serviceName requested of type $serviceClass")
        }
        return Retrofit.Builder()
            .baseUrl(serviceConfiguration.getServiceEndpoint(serviceName, dynamicName).url)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
            .create(serviceClass.java)
    }
}