package com.example.demo.controller;

import com.example.demo.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class TrackingControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrackingService service;
}
