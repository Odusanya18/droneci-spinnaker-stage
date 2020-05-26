package com.github.odusanya18.droneci.stage.pipeline

import com.github.odusanya18.droneci.stage.models.definition.CIStageDefinition
import com.github.odusanya18.droneci.stage.controller.DroneCIController
import com.github.odusanya18.droneci.stage.tasks.MonitorDroneCITask
import com.github.odusanya18.droneci.stage.tasks.StartDroneCITask
import com.netflix.spinnaker.orca.api.pipeline.graph.TaskNode.Builder
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import com.netflix.spinnaker.orca.api.pipeline.graph.StageDefinitionBuilder
import com.netflix.spinnaker.kork.plugins.api.spring.PrivilegedSpringPlugin
import com.netflix.spinnaker.orca.api.pipeline.CancellableStage
import org.pf4j.Extension
import org.pf4j.PluginWrapper
import org.springframework.beans.factory.support.BeanDefinitionRegistry

class DroneCIPlugin(wrapper: PluginWrapper) : PrivilegedSpringPlugin(wrapper) {
    override fun registerBeanDefinitions(registry: BeanDefinitionRegistry) {
        listOf(
            beanDefinitionFor(DroneCIController::class.java)
        ).forEach {
            registerBean(it, registry)
        }
    }

    @Extension
    class DroneCIStage : StageDefinitionBuilder, CancellableStage {
        override fun taskGraph(stage: StageExecution, builder: Builder) {
            stage.mapTo(CIStageDefinition::class.java)
            builder
                .withTask("startDroneCITask", StartDroneCITask::class.java)
                .withTask("monitorDroneCITask", MonitorDroneCITask::class.java)
                .withTask("stopDroneCITask", MonitorDroneCITask::class.java)
        }

        override fun cancel(stage: StageExecution?): CancellableStage.Result {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    companion object {
        val HTTP_CLIENT = "drone-ci:executorproxy"
    }
}