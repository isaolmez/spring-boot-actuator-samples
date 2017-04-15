package com.isa.spring.boot.actuator.controllers;

import com.isa.spring.boot.actuator.services.counter.ServiceWithCounter;
import com.isa.spring.boot.actuator.services.gauge.ServiceWithGauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ServiceWithCounter serviceWithCounter;

    private final ServiceWithGauge serviceWithGauge;

    @Autowired
    public HomeController(ServiceWithCounter serviceWithCounter, ServiceWithGauge serviceWithGauge) {
        this.serviceWithCounter = serviceWithCounter;
        this.serviceWithGauge = serviceWithGauge;
    }

    @GetMapping("/count")
    public String count() {
        return serviceWithCounter.get();
    }

    @GetMapping("/gauge")
    public String gauge() {
        return serviceWithGauge.get();
    }
}
