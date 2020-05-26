package com.github.odusanya18.droneci.stage.client.definition

interface RetryableStageDefinition {
    val consecutiveErrors: Int?
}