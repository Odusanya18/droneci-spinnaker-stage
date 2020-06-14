package com.github.odusanya18.droneci.client.internal

import com.github.odusanya18.droneci.services.internal.IgorService
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

@Component
@ConditionalOnExpression("\${igor.enabled:true}")
class ServiceClient(@Value("\${igor.base-url}") var igorBaseUrl: String = "") {
    private val converter = GsonConverterFactory.create()
    private val okHttpClient = OkHttpClient()
    val igorService by lazy { createService(igorBaseUrl, IgorService::class) }

    private fun <S : Any> createService(baseUrl: String, serviceClass: KClass<S>) = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
            .create(serviceClass.java)
}