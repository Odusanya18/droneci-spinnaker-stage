package com.github.odusanya18.droneci.stage.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.RestController


@RestController
class DroneCIController {

    @GetMapping("/drone-ci/repos")
    fun repos() = ""
}