package com.org.KubernetesWithSpring.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String getData(){
        return "Hello World from Kubernetes";
    }
}
