package com.github.odusanya18.droneci.stage.models

import kotlin.String

typealias Users = List<User>

/**
 * The list of Drone Users
 *
 * The root schema comprises the entire JSON document.
 */
data class User (
    /**
     * An explanation about the purpose of this instance.
     */
    val active: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val admin: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val avatar: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val created: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val email: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val id: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val lastLogin: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val login: String,

    /**
     * An explanation about the purpose of this instance.
     */
    val machine: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val synced: Long,

    /**
     * An explanation about the purpose of this instance.
     */
    val syncing: Boolean,

    /**
     * An explanation about the purpose of this instance.
     */
    val updated: Long,
    val token: String?
)
