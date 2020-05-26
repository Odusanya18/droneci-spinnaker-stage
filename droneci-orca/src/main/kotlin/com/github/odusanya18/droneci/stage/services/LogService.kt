package com.github.odusanya18.droneci.stage.services

import com.github.odusanya18.droneci.stage.models.Logs
import retrofit2.http.GET
import retrofit2.http.Path

interface LogService {
    @GET("/api/repos/{owner}/{repo}/builds/{buildNumber}/logs/{stage}/{step}")
    fun stageStepLogs(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("buildNumber") buildNumber: String,
        @Path("stage") stage: String,
        @Path("step") step: String
    ) : Logs
}