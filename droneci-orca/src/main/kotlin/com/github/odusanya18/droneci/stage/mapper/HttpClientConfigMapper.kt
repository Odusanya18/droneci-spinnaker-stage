package com.github.odusanya18.droneci.stage.mapper

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

class HttpClientConfigMapperDTO() : ModelMapper() {
    init {
        configuration.matchingStrategy = MatchingStrategies.LOOSE
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }
}

object HttpClientConfigMapper {
    val mapper = HttpClientConfigMapperDTO()
    inline fun <S, reified T> from(source: S): T = mapper.map(source, T::class.java)
}