package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.models.execution.DroneCIStageExecution
import com.github.odusanya18.droneci.stage.util.TaskUtil
import com.netflix.spinnaker.orca.api.pipeline.Task
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension

@Extension
class DeclineDroneCITask(droneCIProperties: DroneCIProperties) : Task, DroneCIClientAware(droneCIProperties) {
    override fun execute(stage: StageExecution): TaskResult {
        val execution = stage.mapTo(DroneCIStageExecution::class.java)
        val declinedBuild = clientForMaster(execution.master)
                .buildService
                .declineBuild(execution.owner, execution.repoName, execution.buildNumber)
        if (declinedBuild.isSuccessful){
            return TaskUtil.taskResult(ExecutionStatus.SUCCEEDED, "declined: ${execution.buildNumber}")
        }
        return TaskUtil.taskResult(ExecutionStatus.TERMINAL, "failed")
    }
}