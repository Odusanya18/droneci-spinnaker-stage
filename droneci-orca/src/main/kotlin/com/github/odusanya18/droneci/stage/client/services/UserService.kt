package com.github.odusanya18.droneci.stage.client.services

import com.github.odusanya18.droneci.stage.client.model.User
import com.github.odusanya18.droneci.stage.client.model.Users
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/api/users")
    fun listUsers() : Users

    @GET("/api/users/{login}")
    fun infoUser(
        @Path("login") login: String
    ) : User
}