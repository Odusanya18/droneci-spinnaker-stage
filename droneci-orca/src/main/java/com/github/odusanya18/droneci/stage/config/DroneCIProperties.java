package com.github.odusanya18.droneci.stage.config;

import com.github.odusanya18.droneci.stage.models.Master;
import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ExtensionConfiguration("odusanya18.drone-ci")
@ConfigurationProperties("spinnaker.extensibility.plugins.odusanya18.drone-ci")
public class DroneCIProperties {
    private Map<String, Master> masters;
    private Long timeout = 300L;
    private Long backOffPeriod = 500L;

    public Map<String, Master> getMasters() {
        return masters;
    }

    public void setMasters(Map<String, Master> masters) {
        this.masters = masters;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getBackOffPeriod() {
        return backOffPeriod;
    }

    public void setBackOffPeriod(Long backOffPeriod) {
        this.backOffPeriod = backOffPeriod;
    }
}