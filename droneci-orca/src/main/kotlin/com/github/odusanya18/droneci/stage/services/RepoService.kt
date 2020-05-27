package com.github.odusanya18.droneci.stage.services

import com.github.odusanya18.droneci.stage.models.Repos
import retrofit2.Call
import retrofit2.http.GET

interface RepoService {
    @GET("/api/user/repos")
    fun listRepos() : Call<Repos>
}