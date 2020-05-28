package com.github.odusanya18.droneci.stage.models

/**
 * A list of Drone CI Builds
 *
 * A representation of a Drone CI Build
 */
data class Build (
    val action: String,

    /**
     * The hash for the previous commit before this build commit
     */
    val after: String,

    /**
     * The avatar for the commit author
     */
    val authorAvatar: String,

    /**
     * The commit author email
     */
    val authorEmail: String,

    /**
     * The Commit author username
     */
    val authorLogin: String,

    /**
     * The commit author name in full
     */
    val authorName: String,

    /**
     * The hash for the next commit after this build commit
     */
    val before: String,

    /**
     * The time the build was created in time since epoch
     */
    val created: Long,

    /**
     * The event that triggered this build
     */
    val event: String,

    /**
     * The time the build was finished in time since epoch
     */
    val finished: Long,

    /**
     * This is a simple signed long representation of the build id
     */
    val id: Long,

    /**
     * The scm link to the commit diff for this build
     */
    val link: String,

    /**
     * The message on this build's commit
     */
    val message: String,

    /**
     * The number of this build, unsigned long
     */
    val number: Long,

    /**
     * The git ref for this build commit
     */
    val ref: String,

    /**
     * This is a simple signed long representation of the build repo
     */
    val repoID: Long,

    /**
     * The username for the PR sender
     */
    val sender: String,

    /**
     * The source branch for this commit, useful in PRs
     */
    val source: String,

    /**
     * The source repo for this commit, useful in PRs
     */
    val sourceRepo: String,

    val stages: List<Stage>? = null,

    /**
     * The build start time in time since epoch
     */
    val started: Long,

    /**
     * The status of this build
     */
    val status: String,

    /**
     * The target branch for this commit, useful in PRs
     */
    val target: String,

    /**
     * The timestamp for this build
     */
    val timestamp: Long,

    /**
     * What triggered this build
     */
    val trigger: String,

    /**
     * The time the build was updated in time since epoch
     */
    val updated: Long,

    /**
     * The Version id for this build
     */
    val version: Long
)

/**
 * The list of stages in this build
 *
 * A stage in this build
 */
data class Stage (
    /**
     * The CPU arch for the machine on which this stage is executed
     */
    val arch: String,

    /**
     * The build id - The id of this build, unsigned long
     */
    val buildID: Long,

    /**
     * The epoch time passed when the stage was created
     */
    val created: Long,

    /**
     * Ignore current stage error
     */
    val errignore: Boolean,

    /**
     * The exit code for this stage
     */
    val exitCode: Long,

    /**
     * The id of this stage, unsigned long
     */
    val id: Long,

    /**
     * The id of the machine on which the stage is executed
     */
    val machine: String,

    /**
     * The name of this stage
     */
    val name: String,

    /**
     * The build stage number
     */
    val number: Long,

    /**
     * On failure of this stage
     */
    val onFailure: Boolean,

    /**
     * On success of this stage
     */
    val onSuccess: Boolean,

    /**
     * The OS of the machine on which this stage is executed
     */
    val os: String,

    /**
     * The epoch time passed when the stage started
     */
    val started: Long,

    /**
     * The status of this stage
     */
    val status: String,

    val steps: List<Step>,

    /**
     * The epoch time passed when the stage stopped
     */
    val stopped: Long,

    /**
     * The epoch time passed when the stage was created
     */
    val updated: Long,

    /**
     * The version id of the stage, short unsigned
     */
    val version: Long
)

/**
 * The list of steps in a stage
 *
 * A step in a stage
 */
data class Step (
    /**
     * The exit code of the step
     */
    val exitCode: Long,

    /**
     * The id of the step
     */
    val id: Long,

    /**
     * The name of the step
     */
    val name: String,

    /**
     * The number of the step
     */
    val number: Long,

    /**
     * The time step started, time since epoch
     */
    val started: Long,

    /**
     * The status of the step
     */
    val status: String,

    /**
     * This has to do with the step type
     */
    val stepID: Long,

    /**
     * The time step stopped, time since epoch
     */
    val stopped: Long,

    /**
     * The version number of this step
     */
    val version: Long
)
