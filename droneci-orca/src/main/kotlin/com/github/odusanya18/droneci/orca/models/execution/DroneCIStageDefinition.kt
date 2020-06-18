package com.github.odusanya18.droneci.orca.models.execution

import com.github.odusanya18.droneci.models.Build

data class DroneCIStageDefinition (
        val master: String,
        val repo: String,
        val buildInfo: Build?,
        val namespace: String,
        val branch: String?,
        val commit: String?,
        val environment: Map<String, String>?
)