package com.github.odusanya18.droneci.stage.model

class DroneCIStageDefinition() : RetryableStageDefinition {
    override var consecutiveErrors: Int = 0
}