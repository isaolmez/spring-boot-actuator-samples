package com.isa.spring.boot.actuator.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListerEndpoint extends AbstractEndpoint<List<Endpoint>> {
    private List<Endpoint> endpoints;

    @Autowired
    public ListerEndpoint(List<Endpoint> endpoints) {
        super("lister", false);
        this.endpoints = endpoints;
    }

    public List<Endpoint> invoke() {
        return this.endpoints;
    }
}