package com.github.odusanya18.droneci.stage.util

import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus

object TaskUtil {
    fun taskResult(executionStatus: ExecutionStatus, buildInfo: Any): TaskResult {
        return TaskResult
            .builder(executionStatus)
            .context(mapOf("info" to buildInfo))
            .build()
    }
}