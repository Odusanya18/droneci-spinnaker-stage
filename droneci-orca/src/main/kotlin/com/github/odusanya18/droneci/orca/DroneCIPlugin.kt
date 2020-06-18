package com.github.odusanya18.droneci.orca

import com.github.odusanya18.droneci.config.DroneCIProperties
import com.github.odusanya18.droneci.orca.tasks.*
import com.netflix.spinnaker.orca.api.pipeline.CancellableStage
import com.netflix.spinnaker.orca.api.pipeline.CancellableStage.Result
import com.netflix.spinnaker.orca.api.pipeline.graph.StageDefinitionBuilder
import com.netflix.spinnaker.orca.api.pipeline.graph.TaskNode.Builder
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper

class DroneCIPlugin(wrapper: PluginWrapper) : Plugin(wrapper)

@Extension
class DroneCIStage(droneCIProperties: DroneCIProperties) : StageDefinitionBuilder, CancellableStage {
    private val stopDroneCITask = StopDroneCITask(droneCIProperties)

    override fun taskGraph(stage: StageExecution, builder: Builder) {
        builder
            .withTask("startDroneCITask", StartDroneCITask::class.java)
            .withTask("monitorDroneCITask", MonitorDroneCITask::class.java)
    }

    override fun cancel(stage: StageExecution): Result {
        val result = stopDroneCITask.execute(stage)
        stage.context = result.context
        return Result(stage, stage.context)
    }
}