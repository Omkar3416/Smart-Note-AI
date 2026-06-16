package com.omkar.smartnotes.controller;

import com.omkar.smartnotes.service.AiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiTestController {

    private final AiService aiService;

    public AiTestController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/test-ai")
    public String test() {
        return aiService.summarize("Spring Boot is a Java framework for building web apps.");
    }
}