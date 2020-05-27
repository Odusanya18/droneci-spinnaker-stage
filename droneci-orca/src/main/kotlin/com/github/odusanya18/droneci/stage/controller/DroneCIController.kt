package com.github.odusanya18.droneci.stage.controller

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/drone-ci/masters")
class DroneCIController(droneCIProperties: DroneCIProperties): DroneCIClientAware(droneCIProperties) {

    @GetMapping
    fun masters() =
        droneCIProperties
            .masters
            .keys

    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String) =
        clientForMaster(master)
            ?.repoService
            ?.listRepos()
            ?.map { repo -> repo.namespace }
            ?.distinct()
            .orEmpty()

    @GetMapping("/{master}/repos/{namespace}")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String) =
        clientForMaster(master)
            ?.repoService
            ?.listRepos()
            ?.filter { repo -> repo.namespace == namespace }
            .orEmpty()
}