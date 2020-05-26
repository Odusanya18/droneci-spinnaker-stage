package com.github.odusanya18.droneci.stage.controller

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/drone-ci")
class DroneCIController(pluginSdks: PluginSdks): DroneCIClientAware(pluginSdks) {

    @GetMapping("/masters")
    fun masters() =
        droneCIProperties.masters.keys

    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String) =
        clientForMaster(master)?.repoService?.listRepos()?.map { repo -> repo.namespace }?.distinct()

    @GetMapping("/{master}/repos/{namespace}")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String) =
        clientForMaster(master)?.repoService?.listRepos()?.filter { repo -> repo.namespace == namespace }
}