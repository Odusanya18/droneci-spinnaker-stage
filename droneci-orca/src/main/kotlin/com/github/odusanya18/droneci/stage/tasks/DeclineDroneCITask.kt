package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.models.execution.DroneCIStageExecution
import com.github.odusanya18.droneci.stage.util.TaskUtil.task
import com.github.odusanya18.droneci.stage.util.TaskUtil.taskResult
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
                .execute()
        if (declinedBuild.isSuccessful){
            return taskResult(ExecutionStatus.SUCCEEDED, task("success", execution.buildNumber))
        }
        return taskResult(ExecutionStatus.TERMINAL, task("failed", execution.buildNumber))
    }
}