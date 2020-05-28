package com.github.odusanya18.droneci.stage.client

import com.github.odusanya18.droneci.stage.services.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class DroneCIClient(private val host: String, private val token: String) {
    private val converter = GsonConverterFactory.create()
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor { chain -> oauthInterceptor.intercept(chain) }
        .build()

    val buildService = createService(BuildService::class)
    val repoService  = createService(RepoService::class)

    private val oauthInterceptor = Interceptor { chain ->
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build())
    }

    private fun <S : Any> createService(serviceClass: KClass<S>): S {
        return Retrofit.Builder()
            .baseUrl(host)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
            .create(serviceClass.java)
    }
}
