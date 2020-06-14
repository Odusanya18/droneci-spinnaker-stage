package com.github.odusanya18.droneci.orca.tasks

import com.github.odusanya18.droneci.client.DroneCIClientAware
import com.github.odusanya18.droneci.config.DroneCIProperties
import com.github.odusanya18.droneci.orca.models.execution.DroneCIStageExecution
import com.github.odusanya18.droneci.orca.util.TaskUtil.task
import com.github.odusanya18.droneci.orca.util.TaskUtil.taskResult
import com.netflix.spinnaker.orca.api.pipeline.Task
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension

@Extension
class StopDroneCITask(droneCIProperties: DroneCIProperties) : Task, DroneCIClientAware(droneCIProperties) {

    override fun execute(stage: StageExecution): TaskResult {
        val execution = stage.mapTo(DroneCIStageExecution::class.java)
        val client = clientForMaster(execution.master)

        val cancelledBuild = client
                .buildService
                .stopBuild(execution.namespace, execution.buildNumber)
                .execute()
        if (cancelledBuild.isSuccessful){
            return taskResult(ExecutionStatus.SUCCEEDED, task("cancelled", execution.buildNumber))
        }
        return taskResult(ExecutionStatus.TERMINAL, task("failed", execution.buildNumber))
    }
}