package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClient
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus

open class CommonTask(pluginSdks: PluginSdks) {
    val droneCIProperties: DroneCIProperties = pluginSdks
        .yamlResourceLoader()
        .loadResource("drone-ci.yml", DroneCIProperties::class.java)
    val client = DroneCIClient(droneCIProperties.baseUrl, droneCIProperties.token, droneCIProperties.refresh)

    protected fun taskResult(executionStatus: ExecutionStatus, buildInfo: Any): TaskResult {
        return TaskResult
            .builder(executionStatus)
            .context(mapOf("info" to buildInfo))
            .build()
    }
}