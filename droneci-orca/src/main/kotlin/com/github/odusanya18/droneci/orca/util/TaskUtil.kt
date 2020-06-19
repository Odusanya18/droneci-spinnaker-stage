package com.github.odusanya18.droneci.orca.util

import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus

object TaskUtil {
    fun task(status: String, buildNumber: Int) = mapOf(
        "action" to status,
        "number" to buildNumber.toString())

    fun taskResult(executionStatus: ExecutionStatus, buildInfo: Any): TaskResult {
        return TaskResult
            .builder(executionStatus)
            .context(mapOf("buildInfo" to buildInfo))
            .build()
    }
}