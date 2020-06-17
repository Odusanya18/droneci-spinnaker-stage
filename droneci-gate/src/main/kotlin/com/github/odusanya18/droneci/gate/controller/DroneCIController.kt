package com.github.odusanya18.droneci.gate.controller

import com.github.odusanya18.droneci.client.internal.ServiceClient
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
@RequestMapping("/drone-ci/masters")
class DroneCIController(private val serviceClient: ServiceClient) {
    @ApiOperation(value = "Retrieve a list of Drone Masters", response = List::class)
    @GetMapping
    private fun masters() : List<String> {
        val masters = serviceClient
            .igorService
            .getMasters()
            .execute()
        if (masters.isSuccessful) {
            return masters
                .body()
                .orEmpty()
        }
        throw DroneIgorException(masters.message())
    }

    @ApiOperation(value = "Retrieve a list of Drone Namespaces by Master", response = List::class)
    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String): List<String> {
        val namespaces = serviceClient
            .igorService
            .getNamespacesByMaster(master)
            .execute()
        if (namespaces.isSuccessful){
            return namespaces
                .body()
                .orEmpty()
        }
        throw DroneIgorException(namespaces.message())
    }

    @ApiOperation(value = "Retrieve a list of Drone Repos by Namespace", response = List::class)
    @GetMapping("/{master}/namespaces/{namespace}/repos")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String): List<String> {
        val repos = serviceClient
            .igorService
            .getReposByNamespace(master, namespace)
            .execute()
        if (repos.isSuccessful){
            return repos
                .body()
                .orEmpty()
        }
        throw DroneIgorException(repos.message())
    }

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Request to igor failed")
    private class DroneIgorException internal constructor(message: String) : IOException(message)
}