package com.github.odusanya18.igor.controller

import com.github.odusanya18.droneci.client.DroneCIClientAware
import com.github.odusanya18.droneci.config.DroneCIProperties
import com.github.odusanya18.droneci.models.Master
import com.github.odusanya18.droneci.models.Repo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
@RequestMapping("/drone-ci/masters")
class DroneCIController(droneCIProperties: DroneCIProperties) : DroneCIClientAware(droneCIProperties) {
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
        throw DroneMasterException(
            repos.message()
        )
    }

    @GetMapping("/{master}/namespaces/{namespace}/repos")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String): List<String> {
        val repos = clientForMaster(master)
                .repoService
                .listRepos()
                .execute()
        if (repos.isSuccessful) {
            return repos
                    .body()
                    ?.filter { repo : Repo -> repo.namespace == namespace }
                    ?.map(Repo::name)
                    ?.distinct()
                    .orEmpty()
        }
        throw DroneMasterException(
            repos.message()
        )
    }

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Request to master failed")
    private class DroneMasterException internal constructor(message: String) : IOException(message)
}