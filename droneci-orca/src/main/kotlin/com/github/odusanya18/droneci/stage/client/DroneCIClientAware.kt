package com.github.odusanya18.droneci.stage.client

import com.github.odusanya18.droneci.stage.config.DroneCIProperties

open class DroneCIClientAware(val droneCIProperties: DroneCIProperties) {

    protected fun clientForMaster(masterName: String) =
        droneCIProperties.masters[masterName]?.let { DroneCIClient(it.baseUrl, it.token, it.refresh) }
}