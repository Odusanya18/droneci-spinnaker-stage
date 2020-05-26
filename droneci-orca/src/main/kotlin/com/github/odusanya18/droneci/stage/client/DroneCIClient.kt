package com.github.odusanya18.droneci.stage.client

import com.github.odusanya18.droneci.stage.client.services.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.ZonedDateTime
import kotlin.reflect.KClass

class DroneCIClient(private val host: String, var token: String, private val refresh: Boolean = false) {
    private val converter = GsonConverterFactory.create()
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor { chain -> oauthInterceptor.intercept(chain) }
        .build()

    private val tokenService = createService(TokenService::class)
    val buildService = createService(BuildService::class)
    val repoService  = createService(RepoService::class)
    val userService = createService(UserService::class)
    val logService = createService(LogService::class)

    private var tokenExpiration = ZonedDateTime.now()

    private val oauthInterceptor = Interceptor { chain ->
        refreshTokenIfNecessary()
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build())
    }

    private fun refreshTokenIfNecessary() {
        if (refresh && tokenExpiration.isBefore(ZonedDateTime.now())) {
            refreshToken()
        }
    }

    private fun refreshToken() {
        tokenService.refreshToken().token?.let { refreshToken ->
            tokenExpiration = ZonedDateTime.now().plusHours(1)
            token = refreshToken
        }
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
