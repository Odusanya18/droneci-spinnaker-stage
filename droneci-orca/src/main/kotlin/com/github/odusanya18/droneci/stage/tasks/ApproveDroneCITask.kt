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
class ApproveDroneCITask(droneCIProperties: DroneCIProperties) : Task, DroneCIClientAware(droneCIProperties) {
    override fun execute(stage: StageExecution): TaskResult {
        val stageDefinition = stage.mapTo(CIStageDefinition::class.java)

        clientForMaster(stageDefinition.master)?.let { client ->
            stageDefinition.context?.let { context ->
                val approvedBuild = client
                    .buildService
                    .approveBuild(
                        context["owner"] as String,
                        context["repo"] as String,
                        context["buildNumber"] as String
                    )
                if (approvedBuild.isSuccessful){
                    return TaskUtil.taskResult(ExecutionStatus.SUCCEEDED, "approved: ${context["buildNumber"]}")
                }
            }
        }
        return TaskUtil.taskResult(ExecutionStatus.TERMINAL, "failed")
    }
}