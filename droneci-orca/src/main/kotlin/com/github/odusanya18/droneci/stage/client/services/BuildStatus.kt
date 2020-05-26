package com.github.odusanya18.droneci.stage.client.services

enum class BuildStatus(s: String) {
    SUCCESS("success"),
    FAILED("failed"),
    PENDING("pending"),
    RUNNING("running")
}