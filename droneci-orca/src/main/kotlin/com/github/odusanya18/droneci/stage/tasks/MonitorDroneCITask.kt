package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.models.definition.CIStageDefinition
import com.github.odusanya18.droneci.stage.models.BuildStatus
import com.github.odusanya18.droneci.stage.util.TaskUtil.taskResult
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.orca.api.pipeline.RetryableTask
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension
import java.util.concurrent.TimeUnit

@Extension
class MonitorDroneCITask(pluginSdks: PluginSdks) : RetryableTask, DroneCIClientAware(pluginSdks) {

    override fun getTimeout() = TimeUnit.SECONDS.toMillis(droneCIProperties.timeout)
    override fun getBackoffPeriod() = TimeUnit.SECONDS.toMillis(droneCIProperties.backOffPeriod)

    override fun execute(stage: StageExecution): TaskResult {
        val stageDefinition = stage.mapTo(CIStageDefinition::class.java)
        clientForMaster(stageDefinition.master)?.let {client->
            stageDefinition.context?.let { context ->
                val infoBuild = client
                    .buildService
                    .infoBuild(
                        context["owner"] as String,
                        context["repo"] as String,
                        context["buildNumber"] as String
                    )
                return when (BuildStatus.valueOf(infoBuild.status)) {
                    BuildStatus.SUCCESS -> taskResult(ExecutionStatus.SUCCEEDED, infoBuild)
                    BuildStatus.FAILED -> taskResult(ExecutionStatus.TERMINAL, infoBuild)
                    BuildStatus.PENDING -> taskResult(ExecutionStatus.NOT_STARTED, infoBuild)
                    BuildStatus.RUNNING -> taskResult(ExecutionStatus.RUNNING, infoBuild)
                }
            }
        }
        return taskResult(ExecutionStatus.TERMINAL, "failed")
    }

}