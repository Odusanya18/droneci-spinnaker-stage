package com.github.odusanya18.droneci.stage.services

import com.github.odusanya18.droneci.stage.models.Build
import retrofit2.Call
import retrofit2.http.*


interface BuildService {
    @GET("/api/repos/{owner}/{repo}/builds/{buildNumber}")
    fun infoBuild(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?,
        @Path("buildNumber") buildNumber: Long?
    ) : Build

    @POST("/api/repos/{namespace}/{name}/builds")
    fun createBuild(
        @Path("namespace") namespace: String?,
        @Path("name") name: String?,
        @Query("branch") branch: String? = null,
        @Query("commit") commit: String? = null,
        @QueryMap environment: Map<String, String>? = null
    ) : Build

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}/promote")
    fun promoteBuild(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("buildNumber") buildNumber: String,
        @Query("target") target: String,
        @QueryMap environment: Map<String, String>? = null
    ) : Call<Build>

    @DELETE("/api/repos/{owner}/{repo}/builds/{buildNumber}")
    fun stopBuild(
        @Path("owner") owner: String?,
        @Path("buildNumber") buildNumber: Long?
    ) : Call<Void>

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}/approve")
    fun approveBuild(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?,
        @Path("buildNumber") buildNumber: Long?
    ) : Call<Void>

    @POST("/api/repos/{owner}/{repo}/builds/{buildNumber}/decline")
    fun declineBuild(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?,
        @Path("buildNumber") buildNumber: Long?
    ) : Call<Void>
}