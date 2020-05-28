package com.github.odusanya18.droneci.stage.models

import kotlin.String

/**
 * The list of Drone CI Repos
 *
 * The root schema comprises the entire JSON document.
 */
data class Repo (
    /**
     * An explanation about the purpose of this instance.
     */
    val active: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val configPath: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val counter: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val created: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val defaultBranch: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val gitHTTPURL: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val gitSSHURL: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val id: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val link: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val name: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val namespace: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val private: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val protected: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val scm: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val slug: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val synced: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val timeout: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val trusted: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val uid: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val updated: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val userID: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val version: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val visibility: String
)
