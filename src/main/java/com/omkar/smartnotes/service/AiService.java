package com.omkar.smartnotes.service;

import reactor.core.publisher.Flux;

public interface AiService {

    String summarize(String noteContent);
    String explain(String topic);

    String improve(String note);

    Flux<String> streamSummary(String noteContent);

    String chat(String message);
}