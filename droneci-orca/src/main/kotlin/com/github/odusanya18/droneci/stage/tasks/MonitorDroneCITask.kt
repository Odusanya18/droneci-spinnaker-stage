package com.github.odusanya18.droneci.stage.tasks

import com.netflix.spinnaker.orca.api.pipeline.RetryableTask
import com.netflix.spinnaker.orca.api.pipeline.Task
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution

class MonitorDroneCITask : RetryableTask {
    override fun getTimeout(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBackoffPeriod(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(stage: StageExecution): TaskResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}