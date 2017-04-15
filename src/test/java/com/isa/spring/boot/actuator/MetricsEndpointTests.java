package com.isa.spring.boot.actuator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MetricsEndpointTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldAccess() throws Exception {
        mockMvc.perform(get("/metrics"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchInfo() throws Exception {
        mockMvc.perform(get("/metrics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mem").exists())
                .andExpect(jsonPath("$.processors").exists());
    }

    @Test
    public void shouldFetchCustomCounter() throws Exception {
        mockMvc.perform(get("/count")).andExpect(status().isOk());
        mockMvc.perform(get("/metrics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['counter.customCounter']").exists());
    }

    @Test
    public void shouldFetchCustomGauge() throws Exception {
        mockMvc.perform(get("/gauge")).andExpect(status().isOk());
        mockMvc.perform(get("/metrics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['gauge.customGauge']").exists())
                .andExpect(jsonPath("$.['gauge.customGauge']").value(10));;

    }
}
