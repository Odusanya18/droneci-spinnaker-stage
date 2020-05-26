package com.github.odusanya18.droneci.stage.controllers

import com.github.odusanya18.droneci.stage.client.DroneCIClient
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/drone-ci")
class DroneCIController(pluginSdks: PluginSdks) {
    private val droneCIClient: DroneCIClient
    init {
        val droneCIProperties = pluginSdks
            .yamlResourceLoader()
            .loadResource("drone-ci.yml", DroneCIProperties::class.java)
        droneCIClient = DroneCIClient(droneCIProperties.baseUrl, droneCIProperties.token, droneCIProperties.refresh)
    }

    @GetMapping("/namespaces")
    fun namespaces() = droneCIClient
        .repoService
        .listRepos()
        .map { repo -> repo.namespace }
        .distinct()

    @GetMapping("/repos/{namespace}")
    fun reposByNamespace(@PathVariable("namespace") namespace: String) = droneCIClient
        .repoService
        .listRepos()
        .filter { repo -> repo.namespace == namespace }
}