package com.sejun.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HealthCheck {

    @GetMapping("health")
    public String healthCheck() {
        return "ok";
    }
}
