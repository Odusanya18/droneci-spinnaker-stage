package com.github.odusanya18.droneci.stage.client

import com.github.odusanya18.droneci.stage.config.DroneCIProperties

open class DroneCIClientAware(val droneCIProperties: DroneCIProperties) {

    protected fun clientForMaster(masterName: String) =
            droneCIProperties.masters?.get(masterName)?.let { master ->
                        DroneCIClient(master.baseUrl, master.token, master.refresh)
            }
}