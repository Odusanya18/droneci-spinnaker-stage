package com.github.odusanya18.droneci.stage.controller

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.models.Repos
import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController()
@RequestMapping("/drone-ci/masters")
class DroneCIController(pluginSdks: PluginSdks): DroneCIClientAware(pluginSdks) {

    @GetMapping
    fun masters() =
        droneCIProperties.masters.keys

    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String) : List<String> {
        val namespaces = clientForMaster(master)
            ?.repoService
            ?.listRepos()
            ?.map { repo -> repo.namespace }
            ?.distinct()
        return namespaces.orEmpty()
    }

    @GetMapping("/{master}/repos/{namespace}")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String) : Repos {
        val repos = clientForMaster(master)
            ?.repoService
            ?.listRepos()
            ?.filter { repo -> repo.namespace == namespace }
        return repos.orEmpty()
    }

}