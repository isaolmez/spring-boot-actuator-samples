package com.isa.spring.boot.actuator.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomEndpoint implements Endpoint<List<String>> {

    private final Environment environment;

    @Autowired
    public CustomEndpoint(Environment environment) {
        this.environment = environment;
    }

    public String getId() {
        return "customEndpoint";
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isSensitive() {
        return false;
    }

    public List<String> invoke() {
        List<String> messages = new ArrayList<String>();
        messages.add(String.format("Actives profiles: %s", Arrays.toString(environment.getActiveProfiles())));
        return messages;
    }
}