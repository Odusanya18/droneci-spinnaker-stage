package com.github.odusanya18.droneci.stage.config

import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration
import com.netflix.spinnaker.kork.plugins.api.httpclient.HttpClientConfig

@ExtensionConfiguration("drone-ci")
data class DroneCIProperties (
    val baseUrl: String,
    val token: String,
    val refresh: Boolean,
    val timeout: Long,
    val backOffPeriod: Long
)