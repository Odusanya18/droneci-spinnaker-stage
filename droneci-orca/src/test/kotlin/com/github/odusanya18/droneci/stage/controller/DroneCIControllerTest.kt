package com.github.odusanya18.droneci.stage.controller

import com.github.odusanya18.droneci.stage.config.DroneCIProperties
import com.github.odusanya18.droneci.stage.config.DroneCISecurityConfig
import com.github.odusanya18.droneci.stage.models.Master
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user

@WebMvcTest(DroneCIController::class)
@ContextConfiguration(classes = [
    DroneCIProperties::class,
    DroneCISecurityConfig::class,
    DroneCIController::class
])
class DroneCIControllerTest {
    @Autowired
    private val mvc: MockMvc? = null
    @MockBean
    private val droneCIProperties: DroneCIProperties? = null

    private val baseUrl = "/drone-ci/masters"

    @Test
    fun givenNoAuth_thenReturn403() {
        mvc!!.perform(get(baseUrl))
            .andExpect(status().isForbidden)
    }

    @Test
    fun givenNoMasters_thenReturn404() {
        droneCIProperties?.setMasters(emptyList())
        mvc!!
            .perform(get(baseUrl)
                .with(user("test-user"))
                .contentType("application/json"))
            .andExpect(status().isNotFound)
            .andExpect(status().reason(`is`("Masters not configured")))
    }

    @Test
    fun getMasters_thenReturnMasters() {
        droneCIProperties?.setMasters(
            listOf(Master("test-master", "http://test.drone-ci", "1234")))
        mvc!!.perform(get(baseUrl)
            .with(user("test-user")))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$", hasSize<List<String>>(1)))
            .andExpect(jsonPath("$[0]", `is`("test-master")))
    }
}