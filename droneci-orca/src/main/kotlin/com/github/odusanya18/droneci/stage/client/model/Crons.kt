package com.github.odusanya18.droneci.stage.client.model

import kotlin.String

typealias Crons = ArrayList<Cron>

/**
 * A list of Drone CI CronJobs.
 *
 * A representation of a Drone CI Cron
 */
data class Cron (
    /**
     * The cron repo branch this is triggered on
     */
    val branch: String,

    /**
     * The cron creation time represented in time since epoch
     */
    val created: Long,

    /**
     * Determines whether cron is disabled or not
     */
    val disabled: Boolean,

    /**
     * An event that would trigger this cron.
     */
    val event: String,

    /**
     * The cron expression in general unix format.
     */
    val expr: String,

    /**
     * This is a simple signed long representation of the cron job id
     */
    val id: Long,

    /**
     * The name of the cron job
     */
    val name: String,

    /**
     * The next cron execution time represented in time since epoch
     */
    val next: Long,

    /**
     * The previous cron execution time represented in time since epoch
     */
    val prev: Long,

    /**
     * This is a simple signed long representation of the cron repo id
     */
    val repoID: Long,

    /**
     * The cron updated time - The cron updated time represented in time since epoch
     */
    val updated: Long,

    /**
     * The cron version id short
     */
    val version: Long
)
