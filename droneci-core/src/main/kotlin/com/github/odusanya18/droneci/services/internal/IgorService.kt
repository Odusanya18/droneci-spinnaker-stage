package com.github.odusanya18.droneci.services.internal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IgorService {
    @GET("/drone-ci/masters")
    fun getMasters() : Call<List<String>>

    @GET("/drone-ci/masters/{master}/namespaces")
    fun getNamespacesByMaster(
        @Path("master") master: String
    ) : Call<List<String>>

    @GET("/drone-ci/masters/{master}/namespaces/{namespace}/repos")
    fun getReposByNamespace(
        @Path("master") master: String,
        @Path("namespace") namespace: String
    ) : Call<List<String>>
}