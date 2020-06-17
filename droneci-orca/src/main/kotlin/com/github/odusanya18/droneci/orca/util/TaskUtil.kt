package com.github.odusanya18.droneci.orca.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus

object TaskUtil {
    fun task(status: String, buildNumber: Long?) = mapOf(
        "action" to status,
        "number" to buildNumber.toString())

    private inline fun <I, reified O> I.convert(): O {
        val gson = Gson()
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }

    private fun <T> T.serializeToMap(): Map<String, Any> {
        return convert()
    }

    fun taskResult(executionStatus: ExecutionStatus, buildInfo: Any): TaskResult {
        return TaskResult
            .builder(executionStatus)
            .context(buildInfo.serializeToMap())
            .build()
    }
}