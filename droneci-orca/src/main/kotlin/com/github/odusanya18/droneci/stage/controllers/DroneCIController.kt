package com.github.odusanya18.droneci.stage.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/drone-ci")
class DroneCIController {

    @GetMapping("/repos")
    fun repos() = ""
}