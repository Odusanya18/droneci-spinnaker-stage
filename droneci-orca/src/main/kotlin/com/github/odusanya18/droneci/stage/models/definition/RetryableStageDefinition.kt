package com.github.odusanya18.droneci.stage.models.definition

interface RetryableStageDefinition {
    val consecutiveErrors: Int?
}