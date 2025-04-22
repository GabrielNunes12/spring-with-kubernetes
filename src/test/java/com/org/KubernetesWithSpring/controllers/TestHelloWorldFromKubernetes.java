package com.org.KubernetesWithSpring.controllers;

import com.org.KubernetesWithSpring.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TestHelloWorldFromKubernetes {
    private MockMvc mockMvc;

    @Mock
    private MyService myService;

    @InjectMocks
    private HelloWorldFromKubernetes helloWorldFromKubernetes;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(helloWorldFromKubernetes).build();
    }
    @Test
    public void returnHelloWorldFromController() throws Exception {
        when(myService.getData())
                .thenReturn("Hello World from Kubernetes");

        mockMvc.perform(get("/v1/api/hello-world"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World from Kubernetes"));
    }
}
