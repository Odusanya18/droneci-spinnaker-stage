package com.github.odusanya18.droneci.stage.client

import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.netflix.spinnaker.kork.plugins.api.PluginSdks

open class DroneCIClientAware(pluginSdks: PluginSdks) {
    val droneCIProperties: DroneCIProperties = pluginSdks
        .yamlResourceLoader()
        .loadResource("drone-ci.yml", DroneCIProperties::class.java)

    protected fun clientForMaster(masterName: String) =
        droneCIProperties.masters[masterName]?.let { DroneCIClient(it.baseUrl, it.token, it.refresh) }
}