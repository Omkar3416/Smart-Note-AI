package com.omkar.smartnotes.controller;

import com.omkar.smartnotes.service.AiService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:5173")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/summarize")
    public String summarize(
            @RequestParam String note
    ) {
        return aiService.summarize(note);
    }
    @GetMapping("/explain")
    public String explain(
            @RequestParam String topic
    ) {
        return aiService.explain(topic);
    }
    @GetMapping("/improve")
    public String improve(
            @RequestParam String note
    ) {
        return aiService.improve(note);
    }

    @GetMapping(
            value = "/stream",
            produces = "text/event-stream"
    )
    public Flux<String> stream(
            @RequestParam String note
    ) {
        return aiService.streamSummary(note);
    }
    @PostMapping("/chat")
    public String chat(
            @RequestBody String message
    ) {
        return aiService.chat(message);
    }
}