package com.github.odusanya18.droneci.stage.models.execution

enum class BuildStatus(status: String) {
    SUCCESS("success"),
    FAILED("failed"),
    PENDING("pending"),
    RUNNING("running")
}