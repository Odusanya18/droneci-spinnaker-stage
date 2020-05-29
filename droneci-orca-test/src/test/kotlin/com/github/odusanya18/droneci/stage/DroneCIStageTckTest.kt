package com.github.odusanya18.droneci.stage

import com.netflix.spinnaker.kork.plugins.tck.PluginsTck
import com.netflix.spinnaker.kork.plugins.tck.serviceFixture
import dev.minutest.rootContext
import strikt.api.expect
import strikt.assertions.isEqualTo

/**
 * This test demonstrates that the DroneCIPlugin can be loaded by Orca
 * and that DroneCIStage's StageDefinitionBuilder can be retrieved at runtime.
 */
class DroneCIStageTckTest : PluginsTck<OrcaPluginsFixture>() {

    fun tests() = rootContext<OrcaPluginsFixture> {
        context("a running Orca instance") {
            serviceFixture {
                OrcaPluginsFixture()
            }

            defaultPluginTests()

            test("DroneCIStage extension is resolved to the correct type") {
                val stageDefinitionBuilder = stageResolver.getStageDefinitionBuilder(
                    DroneCIStage::class.java.simpleName, "droneCI")
                expect {
                    that(stageDefinitionBuilder.type).isEqualTo("droneCI")
                }
            }

            test("DroneCIApprovalStage extension is resolved to the correct type") {
                val stageDefinitionBuilder = stageResolver.getStageDefinitionBuilder(
                    DroneCIApprovalStage::class.java.simpleName, "droneCIApproval")
                expect {
                    that(stageDefinitionBuilder.type).isEqualTo("droneCIApproval")
                }
            }
        }
    }
}