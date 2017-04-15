package com.isa.spring.boot.actuator.services.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithCounterImpl implements ServiceWithCounter {

    private final CounterService counterService;

    @Autowired
    public ServiceWithCounterImpl(CounterService counterService) {
        this.counterService = counterService;
    }

    @Override
    public String get() {
        counterService.increment("customCounter");
        return "Hello";
    }
}
