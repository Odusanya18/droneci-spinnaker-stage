package com.github.odusanya18.droneci.stage.client.services

import com.github.odusanya18.droneci.stage.client.model.Build
import com.github.odusanya18.droneci.stage.client.model.Builds
import retrofit2.Response
import retrofit2.http.*


interface BuildService {
    @GET("/api/repos/{owner}/{repo}/builds/{buildNumber}")
    fun infoBuild(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("buildNumber") buildNumber: String
    ) : Build

    @POST("/api/repos/{namespace}/{name}/builds")
    fun createBuild(
        @Path("namespace") namespace: String,
        @Path("name") name: String,
        @Query("branch") branch: String? = null,
        @Query("commit") commit: String? = null,
        @QueryMap environment: Map<String, String>? = null
    ) : Build

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}")
    fun restartBuild(
        @Path("owner") owner: String,
        @Path("repo") repo : String,
        @Path("buildNumber") buildNumber: String
    ) : Build

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}/promote")
    fun promoteBuild(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("buildNumber") buildNumber: String,
        @Query("target") target: String,
        @QueryMap environment: Map<String, String>? = null
    ) : Build

    @GET("/api/repos/{owner}/{repo}/builds")
    fun listBuilds(
        @Path("owner") owner: String,
        @Path("repo") repo : String
    ) : Builds

    @DELETE("/api/repos/{owner}/{repo}/builds/{buildNumber}")
    fun stopBuild(
        @Path("owner") owner: String,
        @Path("buildNumber") buildNumber: String
    ) : Response<Void>

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}/approve")
    fun approveBuild(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("buildNumber") buildNumber: String
    ) : Response<Void>

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}/decline")
    fun declineBuild(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("buildNumber") buildNumber: String
    ) : Response<Void>
}