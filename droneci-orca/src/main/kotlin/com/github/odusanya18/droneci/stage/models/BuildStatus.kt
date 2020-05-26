package com.github.odusanya18.droneci.stage.models

enum class BuildStatus(status: String) {
    SUCCESS("success"),
    FAILED("failed"),
    PENDING("pending"),
    RUNNING("running")
}