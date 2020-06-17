package com.github.odusanya18.droneci.orca.util

import com.google.gson.Gson
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TaskUtil {
    fun task(status: String, buildNumber: Long?) = mapOf(
        "action" to status,
        "number" to buildNumber.toString())

    fun taskResult(executionStatus: ExecutionStatus, buildInfo: Any): TaskResult {
        return TaskResult
            .builder(executionStatus)
            .context(mapOf("buildInfo" to buildInfo))
            .build()
    }

    fun buildNumber(ctx: Map<String, Any>): Int {
        val logger = LoggerFactory.getLogger(this.javaClass)
        val gson = Gson()
        logger.info(gson.toJson(ctx))
        val buildInfo = ctx["buildInfo"] as Map<*, *>
        return buildInfo["number"] as Int
    }
}