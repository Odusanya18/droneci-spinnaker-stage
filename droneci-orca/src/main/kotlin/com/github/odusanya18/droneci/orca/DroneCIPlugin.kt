package com.github.odusanya18.droneci.orca

import com.github.odusanya18.droneci.orca.tasks.*
import com.netflix.spinnaker.orca.api.pipeline.graph.TaskNode.Builder
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import com.netflix.spinnaker.orca.api.pipeline.graph.StageDefinitionBuilder
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper

class DroneCIPlugin(wrapper: PluginWrapper) : Plugin(wrapper)

@Extension
class DroneCIStage : StageDefinitionBuilder {
    override fun taskGraph(stage: StageExecution, builder: Builder) {
        builder
            .withTask("startDroneCITask", StartDroneCITask::class.java)
            .withTask("monitorDroneCITask", MonitorDroneCITask::class.java)
            .withTask("stopDroneCITask", StopDroneCITask::class.java)
    }
}

@Extension
class DroneCIApprovalStage : StageDefinitionBuilder {
    override fun taskGraph(stage: StageExecution, builder: Builder) {
        builder
            .withTask("approveDroneCITask", ApproveDroneCITask::class.java)
            .withTask("declineDroneCITask", DeclineDroneCITask::class.java)
    }
}
