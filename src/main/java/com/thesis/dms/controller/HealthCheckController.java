package com.thesis.dms.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
public class HealthCheckController {
    @PostMapping("/health-check")
    @SneakyThrows
    public void getHealth(){
        return;
    }
}
