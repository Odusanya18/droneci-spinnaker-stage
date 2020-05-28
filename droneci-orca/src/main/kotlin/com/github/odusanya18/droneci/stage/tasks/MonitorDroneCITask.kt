package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.models.execution.BuildStatus
import com.github.odusanya18.droneci.stage.models.execution.DroneCIStageExecution
import com.github.odusanya18.droneci.stage.util.TaskUtil.taskResult
import com.netflix.spinnaker.orca.api.pipeline.RetryableTask
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.ExecutionStatus
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension
import java.util.concurrent.TimeUnit

@Extension
class MonitorDroneCITask(droneCIProperties: DroneCIProperties) : RetryableTask, DroneCIClientAware(
    droneCIProperties
) {

    override fun getTimeout() = TimeUnit.SECONDS.toMillis(droneCIProperties.timeout)
    override fun getBackoffPeriod() = TimeUnit.SECONDS.toMillis(droneCIProperties.backOffPeriod)

    override fun execute(stage: StageExecution): TaskResult {
        val execution = stage.mapTo(DroneCIStageExecution::class.java)
        val infoBuild = clientForMaster(execution.master)
                .buildService
                .infoBuild(execution.owner, execution.repoName, execution.buildNumber)
                .execute()
        if (infoBuild.isSuccessful){
            infoBuild
                    .body()
                    ?.let {
                        return when (BuildStatus.valueOf(it.status)) {
                            BuildStatus.SUCCESS -> taskResult(ExecutionStatus.SUCCEEDED, it)
                            BuildStatus.FAILED -> taskResult(ExecutionStatus.TERMINAL, it)
                            BuildStatus.PENDING -> taskResult(ExecutionStatus.NOT_STARTED, it)
                            BuildStatus.RUNNING -> taskResult(ExecutionStatus.RUNNING, it)
                        }
                    }
        }
        return taskResult(ExecutionStatus.TERMINAL, "could not query {${execution.buildNumber}}")
    }

}