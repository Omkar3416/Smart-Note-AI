package com.omkar.smartnotes.service.impl;

import com.omkar.smartnotes.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String summarize(String noteContent) {
        if (noteContent == null || noteContent.isBlank()) {
            return "No content to summarize";
        }

        try {
            return chatClient
                    .prompt()
                    .system("You are a helpful assistant that summarizes notes clearly.")
                    .user(noteContent)
                    .call()
                    .content();
        } catch (Exception e) {
            return "AI is temporarily unavailable. Please try again later.";
        }
    }
}