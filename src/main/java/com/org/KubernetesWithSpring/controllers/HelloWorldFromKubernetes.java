package com.org.KubernetesWithSpring.controllers;

import com.org.KubernetesWithSpring.service.MyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/hello-world")
public class HelloWorldFromKubernetes {
    private MyService myService;
    @GetMapping
    private ResponseEntity<?> helloWorldFromKubernetes() {
        return ResponseEntity.ok(myService.getData());
    }

}
