package com.github.odusanya18.droneci.stage.pipeline

import com.github.odusanya18.droneci.stage.model.DroneCIStageDefinition
import com.github.odusanya18.droneci.stage.tasks.MonitorDroneCITask
import com.github.odusanya18.droneci.stage.tasks.StartDroneCITask
import com.netflix.spinnaker.orca.api.pipeline.CancellableStage
import com.netflix.spinnaker.orca.api.pipeline.CancellableStage.Result
import com.netflix.spinnaker.orca.api.pipeline.graph.TaskNode.Builder
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import com.netflix.spinnaker.orca.api.pipeline.graph.StageDefinitionBuilder
import com.netflix.spinnaker.kork.plugins.api.spring.PrivilegedSpringPlugin
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper
import org.springframework.beans.factory.support.BeanDefinitionRegistry

class DroneCIPlugin(wrapper: PluginWrapper) : PrivilegedSpringPlugin(wrapper) {
    @Extension
    class DroneCIStage : StageDefinitionBuilder, CancellableStage {
        override fun cancel(stage: StageExecution): Result {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun taskGraph(stage: StageExecution, builder: Builder) {
            stage.mapTo(DroneCIStageDefinition::class.java)
            builder
                .withTask("startDroneCITask", StartDroneCITask::class.java)
                .withTask("monitorDroneCITask", MonitorDroneCITask::class.java)
                .withTask("stopDroneCITask", MonitorDroneCITask::class.java)
        }
    }
    companion object {
        val HTTP_CLIENT = "droneciexecutorproxy"
    }

    override fun registerBeanDefinitions(registry: BeanDefinitionRegistry?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}