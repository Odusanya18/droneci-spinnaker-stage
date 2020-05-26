package com.github.odusanya18.droneci.stage.client.definition

import com.fasterxml.jackson.annotation.JsonProperty
import com.netflix.spinnaker.kork.artifacts.model.Artifact
import com.netflix.spinnaker.kork.artifacts.model.ExpectedArtifact

data class CIStageDefinition(
    @param:JsonProperty("master") val master: String,
    @param:JsonProperty("job") val job: String,
    @param:JsonProperty("property") val propertyFile: String,
    @param:JsonProperty("buildNumber") val buildNumber: Int,
    @JsonProperty("buildInfo") val buildInfo: BuildInfo = BuildInfo(),
    @JsonProperty("waitForCompletion") val waitForCompletion: Boolean = true,
    @JsonProperty("expectedArtifacts") val expectedArtifacts: List<ExpectedArtifact> = emptyList(),
    @JsonProperty("consecutiveErrors") override val consecutiveErrors: Int = 0,
    @JsonProperty("context") val context: Map<String, Any>?
) : RetryableStageDefinition {
    data class BuildInfo(@JsonProperty("artifacts") val artifacts: List<Artifact> = emptyList())
}