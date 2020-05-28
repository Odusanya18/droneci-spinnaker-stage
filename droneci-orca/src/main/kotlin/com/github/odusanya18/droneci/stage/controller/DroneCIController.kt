package com.github.odusanya18.droneci.stage.controller

import com.github.odusanya18.droneci.stage.client.DroneCIClientAware
import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.models.Repo
import com.github.odusanya18.droneci.stage.models.Repos
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController()
@RequestMapping("/drone-ci/masters")
class DroneCIController(droneCIProperties: DroneCIProperties): DroneCIClientAware(droneCIProperties) {

    @GetMapping
    fun masters() =
            droneCIProperties
                    .getMasters()
                    .map { master -> master.name }

    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String): List<String> {
        val response = clientForMaster(master)
                .repoService
                .listRepos()
                .execute()
        if (response.isSuccessful) return response
                .body()
                ?.map { repo: Repo -> repo.namespace }
                ?.distinct()
                .orEmpty()
        throw DroneMasterException(response.message())
    }

    @GetMapping("/{master}/namespaces/{namespace}/repos")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String) : Repos {
        val response = clientForMaster(master)
                .repoService
                .listRepos()
                .execute()
        if (response.isSuccessful) return response
                .body()
                ?.filter { repo -> repo.namespace == namespace }
                ?.distinct()
                .orEmpty()
        throw DroneMasterException(response.message())
    }

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Request to master failed")
    class DroneMasterException internal constructor(message: String?) : RuntimeException("Drone: $message")
}