package com.sejun.board.api;

import com.sejun.board.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HealthCheck {

    private Member member;

    @GetMapping("health")
    public String healthCheck() {
        return "ok";
    }
}
