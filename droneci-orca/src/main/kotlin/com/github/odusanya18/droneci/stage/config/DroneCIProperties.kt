/*
 * Copyright 2020 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.odusanya18.droneci.stage.config

import com.github.odusanya18.droneci.stage.models.Master
import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ExtensionConfiguration("odusanya18.drone-ci")
@ConfigurationProperties("spinnaker.extensibility.plugins.odusanya18.drone-ci")
class DroneCIProperties {
    private var masters: List<Master> = emptyList()
    var timeout = 300L
    var backOffPeriod = 500L

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Masters not configured")
    class DroneCIPropertyException internal constructor(message: String?) : IllegalAccessException(message)

    fun getMasters(): List<Master> {
        if (masters.isNotEmpty()) return masters
        throw DroneCIPropertyException("Masters not configured")
    }

    fun getMasterByName(masterName: String): Master {
        val master = masters
                .stream()
                .filter { it.name  == masterName }
                .findFirst()
        if (master.isPresent) return master.get()
        throw DroneCIPropertyException("Master $masterName not configured")
    }

    fun setMasters(masters: List<Master>) {
        this.masters = masters
    }

}