package com.github.odusanya18.droneci.stage.config;

import com.github.odusanya18.droneci.stage.models.Master;
import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ExtensionConfiguration("odusanya18.drone-ci")
@ConfigurationProperties("odusanya18.drone-ci")
@Data
public class DroneCIProperties {
    public Map<String, Master> masters;
    public Long timeout;
    public Long backOffPeriod;
}