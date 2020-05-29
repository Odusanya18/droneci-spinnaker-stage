package com.github.odusanya18.droneci.stage

import com.netflix.spinnaker.kork.plugins.SpinnakerPluginManager
import com.netflix.spinnaker.kork.plugins.internal.PluginJar
import com.netflix.spinnaker.kork.plugins.tck.PluginsTckFixture
import com.netflix.spinnaker.orca.Main
import com.netflix.spinnaker.orca.StageResolver
import com.netflix.spinnaker.orca.notifications.NotificationClusterLock
import com.netflix.spinnaker.orca.pipeline.persistence.ExecutionRepository
import com.netflix.spinnaker.q.Queue
import com.netflix.spinnaker.q.memory.InMemoryQueue
import com.netflix.spinnaker.q.metrics.EventPublisher
import java.io.File
import java.time.Clock
import java.time.Duration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource

class OrcaPluginsFixture : PluginsTckFixture, OrcaTestService() {
    final override val plugins = File("build/plugins")
    final override val enabledPlugin: PluginJar
    final override val disabledPlugin: PluginJar
    final override val versionNotSupportedPlugin: PluginJar

    override val extensionClassNames: MutableList<String> = mutableListOf(
        DroneCIStage::class.java.name, DroneCIApprovalStage::class.java.name
    )

    final override fun buildPlugin(pluginId: String, systemVersionRequirement: String): PluginJar {
        return PluginJar.Builder(plugins.toPath().resolve("$pluginId.jar"), pluginId)
            .pluginClass(DroneCIPlugin::class.java.name)
            .pluginVersion("1.0.0")
            .manifestAttribute("Plugin-Requires", "orca$systemVersionRequirement")
            .extensions(extensionClassNames)
            .build()
    }

    @Autowired
    override lateinit var spinnakerPluginManager: SpinnakerPluginManager

    @Autowired
    lateinit var stageResolver: StageResolver

    @MockBean
    var executionRepository: ExecutionRepository? = null

    @MockBean
    var notificationClusterLock: NotificationClusterLock? = null

    init {
        plugins.delete()
        plugins.mkdir()
        enabledPlugin = buildPlugin("odusanya18.drone-ci.Enabled", ">=1.0.0")
        disabledPlugin = buildPlugin("odusanya18.drone-ci.Disabled", ">=1.0.0")
        versionNotSupportedPlugin = buildPlugin("odusanya18.drone-ci.NotSupported", ">=2.0.0")
    }
}

@SpringBootTest(classes = [Main::class])
@ContextConfiguration(classes = [PluginTestConfiguration::class])
@TestPropertySource(properties = ["spring.config.location=classpath:drone-ci-test.yml"])
abstract class OrcaTestService

@TestConfiguration
internal class PluginTestConfiguration {

    @Bean
    @Primary
    fun queue(clock: Clock?, publisher: EventPublisher?): Queue {
        return InMemoryQueue(
            clock!!, Duration.ofMinutes(1), emptyList(), false, publisher!!)
    }
}