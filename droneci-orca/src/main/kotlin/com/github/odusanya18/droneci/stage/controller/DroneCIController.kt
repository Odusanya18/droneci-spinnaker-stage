package com.github.odusanya18.droneci.stage.controller

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.models.Master
import com.github.odusanya18.droneci.stage.models.Repo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
@RequestMapping("/drone-ci/masters")
open class DroneCIController(droneCIProperties: DroneCIProperties) : DroneCIClientAware(droneCIProperties) {
    @GetMapping
    fun masters() = droneCIProperties
                .getMasters()
                .map(Master::name)

    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String): List<String> {
        val repos = clientForMaster(master)
                .repoService
                .listRepos()
                .execute()
        if (repos.isSuccessful) {
            return repos
                    .body()
                    ?.map(Repo::namespace)
                    ?.distinct()
                    .orEmpty()
        }
        throw DroneMasterException(repos.message())
    }

    @GetMapping("/{master}/namespaces/{namespace}/repos")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String): List<Repo> {
        val repos = clientForMaster(master)
                .repoService
                .listRepos()
                .execute()
        if (repos.isSuccessful) {
            return repos
                    .body()
                    ?.filter { repo -> repo.namespace == namespace }
                    ?.distinct()
                    .orEmpty()
        }
        throw DroneMasterException(repos.message())
    }

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Request to master failed")
    private class DroneMasterException internal constructor(message: String) : IOException(message)
}