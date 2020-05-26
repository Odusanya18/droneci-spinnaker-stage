package com.github.odusanya18.droneci.stage.models.execution

data class DroneCIStageExecution (
    val buildId: String,
    val repoName: String,
    val buildNumber: Int
)