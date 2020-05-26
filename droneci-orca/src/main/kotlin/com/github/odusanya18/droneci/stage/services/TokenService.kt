package com.github.odusanya18.droneci.stage.services

import com.github.odusanya18.droneci.stage.models.User
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenService {

    @POST("/api/user/token")
    fun refreshToken(
        @Query("rotate") rotate: Boolean = true
    ): User
}