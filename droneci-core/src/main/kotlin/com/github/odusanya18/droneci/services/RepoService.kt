package com.github.odusanya18.droneci.services

import com.github.odusanya18.droneci.models.Repo
import retrofit2.Call
import retrofit2.http.GET

interface RepoService {
    @GET("/api/user/repos")
    fun listRepos() : Call<List<Repo>>
}