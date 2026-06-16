package com.omkar.smartnotes.controller.api;

import com.omkar.smartnotes.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatApiController {

    private final AiService aiService;

    public ChatApiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public String chat(
            @RequestBody String message
    ) {
        return aiService.chat(message);
    }
}