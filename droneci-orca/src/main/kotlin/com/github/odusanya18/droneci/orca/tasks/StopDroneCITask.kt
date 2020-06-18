package com.github.odusanya18.droneci.orca.tasks

import com.github.odusanya18.droneci.client.DroneCIClientAware
import com.github.odusanya18.droneci.config.DroneCIProperties
import com.github.odusanya18.droneci.orca.models.execution.DroneCIStageDefinition
import com.github.odusanya18.droneci.orca.util.TaskUtil.task
import com.github.odusanya18.droneci.orca.util.TaskUtil.taskResult
import com.netflix.spinnaker.orca.api.pipeline.Task
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution

class StopDroneCITask(droneCIProperties: DroneCIProperties) : Task, DroneCIClientAware(droneCIProperties) {
    override fun execute(stage: StageExecution): TaskResult {
        val execution = stage.mapTo(DroneCIStageDefinition::class.java)
        val client = clientForMaster(execution.master)

        val cancelledBuild = client
                .buildService
                .stopBuild(execution.namespace, execution.repo, execution.buildInfo?.number)
                .execute()
        if (cancelledBuild.isSuccessful){
            return taskResult(ExecutionStatus.SUCCEEDED, task("cancelled", execution.buildInfo?.number))
        }
        return taskResult(ExecutionStatus.TERMINAL, task("failed", execution.buildInfo?.number))
    }
}