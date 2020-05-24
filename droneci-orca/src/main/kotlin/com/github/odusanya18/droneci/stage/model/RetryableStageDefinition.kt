package com.github.odusanya18.droneci.stage.model


interface RetryableStageDefinition {
    val consecutiveErrors: Int
}