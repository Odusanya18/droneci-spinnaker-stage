package com.github.odusanya18.droneci.stage.models

import kotlin.String

typealias Logs = List<Log>

/**
 * The list of each log line.
 *
 * An explanation about the purpose of this instance.
 */
data class Log (
    /**
     * The stdout,stderr of the log line.
     */
    val out: String,

    /**
     * The pos of the log line.
     */
    val pos: Long,

    /**
     * The time this was logged, time after epoch.
     */
    val time: Long
)
