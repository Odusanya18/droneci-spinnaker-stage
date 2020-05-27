package com.github.odusanya18.droneci.stage.config;

import com.github.odusanya18.droneci.stage.models.config.Master;
import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;
import java.util.Map;

@ExtensionConfiguration("odusanya18.drone-ci")
@ConfigurationProperties("spinnaker.extensibility.plugins.odusanya18.drone-ci")
public class DroneCIProperties {
    private Map<String, Master> masters;
    private Long timeout = 300L;
    private Long backOffPeriod = 500L;

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Master not configured")
    public static class DroneCIPropertyException extends IllegalAccessException {
        DroneCIPropertyException(String message){
            super(message);
        }
    }

    public @Nonnull Map<String, Master> getMasters() throws DroneCIPropertyException {
        if (masters != null && !masters.isEmpty()) return masters;

        throw new DroneCIPropertyException("Masters not configured");
    }

    public @Nonnull Master getMasterByName(String masterName) throws DroneCIPropertyException {
        if (masters != null){
            Master master = masters.get(masterName);
            if (master != null)return master;
        };

        throw new DroneCIPropertyException("Master " + masterName + " not configured");
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