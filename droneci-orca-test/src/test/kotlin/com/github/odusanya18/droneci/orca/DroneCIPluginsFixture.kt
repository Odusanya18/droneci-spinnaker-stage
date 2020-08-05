package com.github.odusanya18.droneci.orca

import com.netflix.spinnaker.kork.plugins.SpinnakerPluginManager
import com.netflix.spinnaker.kork.plugins.internal.PluginJar
import com.netflix.spinnaker.kork.plugins.tck.PluginsTckFixture
import com.netflix.spinnaker.orca.StageResolver
import com.netflix.spinnaker.orca.api.test.OrcaFixture
import java.io.File
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = [
    "spinnaker.extensibility.plugins.odusanya18.drone-ci.enabled=true",
    "spinnaker.extensibility.plugins.odusanya18.drone-ci.disabled=true",
    "spinnaker.extensibility.plugins.odusanya18.drone-ci-not-supported.enabled=true",
    "spinnaker.extensibility.plugins-root-path=build/plugins"
])
class DroneCIPluginsFixture : PluginsTckFixture, OrcaFixture() {
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

    init {
        plugins.delete()
        plugins.mkdir()
        enabledPlugin = buildPlugin("odusanya18.drone-ci.enabled", ">=1.0.0")
        disabledPlugin = buildPlugin("odusanya18.drone-ci.disabled", ">=1.0.0")
        versionNotSupportedPlugin = buildPlugin("odusanya18.drone-ci-not-supported", ">=5000.0.0")
    }
}