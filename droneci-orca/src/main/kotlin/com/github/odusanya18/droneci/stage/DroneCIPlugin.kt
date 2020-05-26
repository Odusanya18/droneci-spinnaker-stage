package com.github.odusanya18.droneci.stage

import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.controller.DroneCIController
import com.github.odusanya18.droneci.stage.tasks.MonitorDroneCITask
import com.github.odusanya18.droneci.stage.tasks.StartDroneCITask
import com.github.odusanya18.droneci.stage.tasks.StopDroneCITask
import com.netflix.spinnaker.orca.api.pipeline.graph.TaskNode.Builder
import com.netflix.spinnaker.orca.api.pipeline.models.StageExecution
import com.netflix.spinnaker.orca.api.pipeline.graph.StageDefinitionBuilder
import com.netflix.spinnaker.kork.plugins.api.spring.PrivilegedSpringPlugin
import org.pf4j.PluginWrapper
import org.springframework.beans.factory.support.BeanDefinitionRegistry

class DroneCIPlugin(wrapper: PluginWrapper) : PrivilegedSpringPlugin(wrapper), StageDefinitionBuilder {
    override fun registerBeanDefinitions(registry: BeanDefinitionRegistry) {
        listOf(
            DroneCIController::class.java,
            DroneCIProperties::class.java
        ).forEach {
            registerBean(beanDefinitionFor(it), registry)
        }
    }

    override fun taskGraph(stage: StageExecution, builder: Builder) {
        builder
            .withTask("startDroneCITask", StartDroneCITask::class.java)
            .withTask("monitorDroneCITask", MonitorDroneCITask::class.java)
            .withTask("stopDroneCITask", StopDroneCITask::class.java)
    }
}