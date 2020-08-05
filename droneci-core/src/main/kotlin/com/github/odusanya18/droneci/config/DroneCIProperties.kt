package com.github.odusanya18.droneci.config

import com.github.odusanya18.droneci.models.Master
import com.netflix.spinnaker.kork.plugins.api.PluginConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus

@PluginConfiguration("odusanya18.drone-ci")
@Component
@ConfigurationProperties(
    "spinnaker.extensibility.plugins.odusanya18.drone-ci." +
            "extensions.odusanya18.drone-ci.config"
)
class DroneCIProperties {
    private var masters: List<Master> = emptyList()
    var timeout = 300L
    var backOffPeriod = 500L

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Masters not configured")
    class DroneCIPropertyException internal constructor(message: String?) : IllegalAccessException(message)

    fun getMasters(): List<Master> {
        if (masters.isNotEmpty()) return masters
        throw DroneCIPropertyException(
            "Masters not configured"
        )
    }

    fun getMasterByName(masterName: String): Master {
        val master = masters
                .stream()
                .filter { it.name  == masterName }
                .findFirst()
        if (master.isPresent) return master.get()
        throw DroneCIPropertyException(
            "Master $masterName not configured"
        )
    }

    fun setMasters(masters: List<Master>) {
        this.masters = masters
    }

}