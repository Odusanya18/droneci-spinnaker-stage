package com.github.odusanya18.droneci.stage.config

import com.github.odusanya18.droneci.stage.mapper.HttpClientConfigMapper
import com.github.odusanya18.droneci.stage.pipeline.DroneCIPlugin
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.kork.plugins.api.httpclient.HttpClient
import java.sql.Time

object DroneCIClient {
    lateinit var stageProperties: DroneCIStageProperties
    fun configure(pluginSdks: PluginSdks): HttpClient {
        stageProperties = pluginSdks
            .yamlResourceLoader()
            .loadResource("droneci.yml", DroneCIStageProperties::class.java)

        pluginSdks
            .http()
            .configure(DroneCIPlugin.HTTP_CLIENT, stageProperties.baseUrl, HttpClientConfigMapper.from(stageProperties))
        return pluginSdks.http().get(DroneCIPlugin.HTTP_CLIENT)
    }
}