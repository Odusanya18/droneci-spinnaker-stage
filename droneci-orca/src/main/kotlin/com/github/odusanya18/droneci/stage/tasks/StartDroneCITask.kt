package com.github.odusanya18.droneci.stage.tasks

import com.github.odusanya18.droneci.stage.config.DroneCIClient
import com.github.odusanya18.droneci.stage.config.DroneCIClient.stageProperties
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.kork.plugins.api.httpclient.HttpClient
import com.netflix.spinnaker.orca.api.pipeline.RetryableTask
import com.netflix.spinnaker.orca.api.pipeline.TaskResult
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension
import java.util.concurrent.TimeUnit

@Extension
class StartDroneCITask(pluginSdks: PluginSdks) : RetryableTask {
    private val httpClient: HttpClient = DroneCIClient.configure(pluginSdks)

    override fun getTimeout() = TimeUnit.SECONDS.toMillis(stageProperties.timeoutSeconds)
    override fun getBackoffPeriod() = TimeUnit.SECONDS.toMillis(stageProperties.backoffPeriodSeconds)

    override fun execute(stage: StageExecution): TaskResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}