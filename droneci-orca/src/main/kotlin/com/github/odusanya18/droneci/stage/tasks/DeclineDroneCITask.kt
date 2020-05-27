package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.models.definition.CIStageDefinition
import com.github.odusanya18.droneci.stage.util.TaskUtil
import com.netflix.spinnaker.orca.api.pipeline.Task
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension

@Extension
class DeclineDroneCITask(droneCIProperties: DroneCIProperties) : Task, DroneCIClientAware(droneCIProperties) {
    override fun execute(stage: StageExecution): TaskResult {
        val stageDefinition = stage.mapTo(CIStageDefinition::class.java)

        clientForMaster(stageDefinition.master)?.let { client ->
            stageDefinition.context?.let { context ->
                val declinedBuild = client
                    .buildService
                    .declineBuild(
                        context["owner"] as String,
                        context["repo"] as String,
                        context["buildNumber"] as String
                    )
                if (declinedBuild.isSuccessful){
                    return TaskUtil.taskResult(ExecutionStatus.SUCCEEDED, "declined: ${context["buildNumber"]}")
                }
            }
        }
        return TaskUtil.taskResult(ExecutionStatus.TERMINAL, "failed")
    }
}