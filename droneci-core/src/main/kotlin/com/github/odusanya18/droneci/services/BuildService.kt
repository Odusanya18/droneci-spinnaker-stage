package com.github.odusanya18.droneci.services

import com.github.odusanya18.droneci.models.Build
import retrofit2.Call
import retrofit2.http.*


interface BuildService {
    @GET("/api/repos/{namespace}/{repo}/builds/{build}")
    fun infoBuild(
        @Path("namespace") namespace: String,
        @Path("repo") repo: String,
        @Path("build") build: Int
    ) : Call<Build>

    @POST("/api/repos/{namespace}/{name}/builds")
    fun createBuild(
        @Path("namespace") namespace: String,
        @Path("name") name: String,
        @Query("branch") branch: String? = null,
        @Query("commit") commit: String? = null,
        @QueryMap environment: Map<String, String>
    ) : Call<Build>

    @POST("/api/repos/{namespace}/{repo}/builds/{build}/promote")
    fun promoteBuild(
        @Path("namespace") namespace: String,
        @Path("repo") repo: String,
        @Path("build") build: String,
        @Query("target") target: String,
        @QueryMap environment: Map<String, String>
    ) : Call<Build>

    @DELETE("/api/repos/{namespace}/{repo}/builds/{build}")
    fun stopBuild(
        @Path("namespace") namespace: String,
        @Path("repo") repo: String,
        @Path("build") build: Int
    ) : Call<Void>

    @POST("/api/repos/{namespace}/{repo}/builds/{build}/approve")
    fun approveBuild(
        @Path("namespace") namespace: String,
        @Path("repo") repo: String,
        @Path("build") build: Long
    ) : Call<Void>

    @POST("/api/repos/{namespace}/{repo}/builds/{build}/decline")
    fun declineBuild(
        @Path("namespace") namespace: String,
        @Path("repo") repo: String,
        @Path("build") build: Long
    ) : Call<Void>
}