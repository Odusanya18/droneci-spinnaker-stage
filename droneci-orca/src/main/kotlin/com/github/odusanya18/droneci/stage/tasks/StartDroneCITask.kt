package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.models.definition.CIStageDefinition
import com.github.odusanya18.droneci.stage.util.TaskUtil.taskResult
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.orca.api.pipeline.RetryableTask
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension
import java.util.concurrent.TimeUnit

@Extension
class StartDroneCITask(pluginSdks: PluginSdks) : RetryableTask, DroneCIClientAware(pluginSdks) {

    override fun getTimeout() = TimeUnit.SECONDS.toMillis(droneCIProperties.timeout)
    override fun getBackoffPeriod() = TimeUnit.SECONDS.toMillis(droneCIProperties.backOffPeriod)

    @Suppress("UNCHECKED_CAST")
    override fun execute(stage: StageExecution): TaskResult {
        val stageDefinition = stage.mapTo(CIStageDefinition::class.java)
        clientForMaster(stageDefinition.master)?.let { client ->
            stageDefinition.context?.let { context ->
                val queuedBuild = client
                    .buildService
                    .createBuild(
                        context["namespace"] as String,
                        context["name"] as String,
                        context["branch"] as String?,
                        context["commit"] as String?,
                        context["environment"] as Map<String, String>?
                    )
                return taskResult(ExecutionStatus.SUCCEEDED, queuedBuild)
            }
        }
        return taskResult(ExecutionStatus.TERMINAL, "failed")
    }
}