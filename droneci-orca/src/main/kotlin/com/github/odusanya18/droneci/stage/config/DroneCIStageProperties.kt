package com.github.odusanya18.droneci.stage.config

import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration
import com.netflix.spinnaker.kork.plugins.api.httpclient.HttpClientConfig

@ExtensionConfiguration("droneci")
data class DroneCIStageProperties (
    /**
     * The base URL of the Drone CI server.
     */
    val baseUrl: String,
    /**
     * Logging level for the HTTP client.
     */
    val clientLoggingLevel: HttpClientConfig.LoggingConfig.LoggingLevel,
    val backoffPeriodSeconds : Long,
    val timeoutSeconds : Long
)