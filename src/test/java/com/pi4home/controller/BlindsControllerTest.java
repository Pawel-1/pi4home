package com.pi4home.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class BlindsControllerTest
{
    private static MockMvc mockMvc;

    @BeforeEach
    public void setup()
    {
        this.mockMvc = standaloneSetup(new BlindsController()).build();
    }

    @Test
    public void testAppIsUpAndRunning() throws Exception
    {
        this.mockMvc.perform(get("/blinds/pinBlindLargeWindowLeft").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andReturn();
    }

}