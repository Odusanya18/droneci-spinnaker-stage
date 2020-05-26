package com.github.odusanya18.droneci.stage.config

import com.github.odusanya18.droneci.stage.models.Master
import com.netflix.spinnaker.kork.plugins.api.PluginConfiguration

@PluginConfiguration("drone-ci")
data class DroneCIProperties (
    val masters: Map<String, Master>,
    val timeout: Long,
    val backOffPeriod: Long
)