package com.github.odusanya18.droneci.models

/**
 * A list of Drone CI Builds
 *
 * A representation of a Drone CI Build
 */
data class Build (
    var action: String? = "",

    /**
     * The hash for the previous commit before this build commit
     */
    var after: String? = "",

    /**
     * The avatar for the commit author
     */
    var authorAvatar: String? = "",

    /**
     * The commit author email
     */
    var authorEmail: String? = "",

    /**
     * The Commit author username
     */
    var authorLogin: String? = "",

    /**
     * The commit author name in full
     */
    var authorName: String? = "",

    /**
     * The hash for the next commit after this build commit
     */
    var before: String? = "",

    /**
     * The time the build was created in time since epoch
     */
    var created: Long? = 0,

    /**
     * The event that triggered this build
     */
    var event: String? = "",

    /**
     * The time the build was finished in time since epoch
     */
    var finished: Long? = 0,

    /**
     * This is a simple signed long representation of the build id
     */
    var id: Long? = 0,

    /**
     * The scm link to the commit diff for this build
     */
    var link: String? = "",
    /**
     * The message on this build's commit
     */
    var message: String? = "",

    /**
     * The number of this build, unsigned long
     */
    var number: Long? = 0,

    /**
     * The git ref for this build commit
     */
    var ref: String? = "",

    /**
     * This is a simple signed long representation of the build repo
     */
    var repoID: Long? = 0,

    /**
     * The username for the PR sender
     */
    var sender: String? = "",

    /**
     * The source branch for this commit, useful in PRs
     */
    var source: String? = "",

    /**
     * The source repo for this commit, useful in PRs
     */
    var sourceRepo: String? = "",

    var stages: List<Stage>? = null,

    /**
     * The build start time in time since epoch
     */
    var started: Long? = 0,

    /**
     * The status of this build
     */
    var status: String,

    /**
     * The target branch for this commit, useful in PRs
     */
    var target: String? = "",

    /**
     * The timestamp for this build
     */
    var timestamp: Long? = 0,

    /**
     * What triggered this build
     */
    var trigger: String? = "",

    /**
     * The time the build was updated in time since epoch
     */
    var updated: Long? = 0,

    /**
     * The Version id for this build
     */
    var version: Long? = 0
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
    var arch: String,

    /**
     * The build id - The id of this build, unsigned long
     */
    var buildID: Long,

    /**
     * The epoch time passed when the stage was created
     */
    var created: Long,

    /**
     * Ignore current stage error
     */
    var errignore: Boolean,

    /**
     * The exit code for this stage
     */
    var exitCode: Long,

    /**
     * The id of this stage, unsigned long
     */
    var id: Long,

    /**
     * The id of the machine on which the stage is executed
     */
    var machine: String,

    /**
     * The name of this stage
     */
    var name: String,

    /**
     * The build stage number
     */
    var number: Long,

    /**
     * On failure of this stage
     */
    var onFailure: Boolean,

    /**
     * On success of this stage
     */
    var onSuccess: Boolean,

    /**
     * The OS of the machine on which this stage is executed
     */
    var os: String,

    /**
     * The epoch time passed when the stage started
     */
    var started: Long,

    /**
     * The status of this stage
     */
    var status: String,

    var steps: List<Step>,

    /**
     * The epoch time passed when the stage stopped
     */
    var stopped: Long,

    /**
     * The epoch time passed when the stage was created
     */
    var updated: Long,

    /**
     * The version id of the stage, short unsigned
     */
    var version: Long
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
    var exitCode: Long,

    /**
     * The id of the step
     */
    var id: Long,

    /**
     * The name of the step
     */
    var name: String,

    /**
     * The number of the step
     */
    var number: Long,

    /**
     * The time step started, time since epoch
     */
    var started: Long,

    /**
     * The status of the step
     */
    var status: String,

    /**
     * This has to do with the step type
     */
    var stepID: Long,

    /**
     * The time step stopped, time since epoch
     */
    var stopped: Long,

    /**
     * The version number of this step
     */
    var version: Long
)
