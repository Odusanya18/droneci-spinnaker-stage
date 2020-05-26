package com.github.odusanya18.droneci.stage.services

import com.github.odusanya18.droneci.stage.models.Repo
import com.github.odusanya18.droneci.stage.models.Repos
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {
    @GET("/api/user/repos")
    fun listRepos() : Repos

    @GET("/api/repos/{owner}/{repo}")
    fun infoRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ) : Repo
}