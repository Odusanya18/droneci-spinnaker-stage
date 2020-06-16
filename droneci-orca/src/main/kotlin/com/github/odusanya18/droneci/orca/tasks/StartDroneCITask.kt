package com.github.odusanya18.droneci.orca.tasks

import com.github.odusanya18.droneci.client.DroneCIClientAware
import com.github.odusanya18.droneci.config.DroneCIProperties
import com.github.odusanya18.droneci.orca.models.execution.DroneCIStageExecution
import com.github.odusanya18.droneci.orca.util.TaskUtil.task
import com.github.odusanya18.droneci.orca.util.TaskUtil.taskResult
import com.netflix.spinnaker.orca.api.pipeline.RetryableTask
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension
import java.util.concurrent.TimeUnit

@Extension
class StartDroneCITask(droneCIProperties: DroneCIProperties) : RetryableTask, DroneCIClientAware(droneCIProperties) {

    override fun getTimeout() = TimeUnit.SECONDS.toMillis(droneCIProperties.timeout)
    override fun getBackoffPeriod() = TimeUnit.SECONDS.toMillis(droneCIProperties.backOffPeriod)

    override fun execute(stage: StageExecution): TaskResult {
        val execution = stage.mapTo(DroneCIStageExecution::class.java)
        val queuedBuild = clientForMaster(execution.master)
                .buildService
                .createBuild(
                        execution.namespace,
                        execution.repo,
                        execution.branch,
                        execution.commit,
                    execution.environment ?: mutableMapOf()
                )
                .execute()
        if (queuedBuild.isSuccessful){
            queuedBuild
                    .body()
                    ?.let {
                        execution.buildNumber = it.number
                return taskResult(ExecutionStatus.SUCCEEDED, it)
            }
        }
        return taskResult(ExecutionStatus.TERMINAL, task("failed", execution.buildNumber))
    }
}