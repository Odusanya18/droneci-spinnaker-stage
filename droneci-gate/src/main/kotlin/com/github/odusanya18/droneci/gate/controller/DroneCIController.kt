package com.github.odusanya18.droneci.gate.controller

import com.github.odusanya18.droneci.client.internal.ServiceClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drone-ci/masters")
class DroneCIController(private val serviceClient: ServiceClient) {
    @GetMapping
    private fun masters() = serviceClient
        .igorService
        .getMasters()
        .execute()
        .body()

    @GetMapping("/{master}/namespaces")
    fun namespaces(@PathVariable("master") master: String) = serviceClient
        .igorService
        .getNamespacesByMaster(master)
        .execute()
        .body()

    @GetMapping("/{master}/namespaces/{namespace}/repos")
    fun reposByNamespace(@PathVariable("master") master: String, @PathVariable("namespace") namespace: String) =
        serviceClient
            .igorService
            .getReposByNamespace(master, namespace)
            .execute()
            .body()
}