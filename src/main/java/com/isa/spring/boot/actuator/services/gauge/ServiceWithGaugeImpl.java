package com.isa.spring.boot.actuator.services.gauge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithGaugeImpl implements ServiceWithGauge {

    private final GaugeService gaugeService;

    @Autowired
    public ServiceWithGaugeImpl(GaugeService gaugeService) {
        this.gaugeService = gaugeService;
    }

    @Override
    public String get() {
        gaugeService.submit("customGauge", 10);
        return "Hello";
    }
}
