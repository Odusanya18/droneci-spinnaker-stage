package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.models.definition.CIStageDefinition
import com.github.odusanya18.droneci.stage.util.TaskUtil.taskResult
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.orca.api.pipeline.Task
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension

@Extension
class StopDroneCITask(pluginSdks: PluginSdks) : Task, DroneCIClientAware(pluginSdks) {

    override fun execute(stage: StageExecution): TaskResult {
        val stageDefinition = stage.mapTo(CIStageDefinition::class.java)
        clientForMaster(stageDefinition.master)?.let { client ->
            stageDefinition.context?.let { context ->
                val cancelledBuild = client
                    .buildService
                    .stopBuild(
                        context["owner"] as String,
                        context["buildNumber"] as String
                    )
                if (cancelledBuild.isSuccessful){
                    return taskResult(ExecutionStatus.CANCELED, "Cancelled: ${context["buildNumber"]}")
                }
            }
        }
        return taskResult(ExecutionStatus.TERMINAL, "failed")
    }
}