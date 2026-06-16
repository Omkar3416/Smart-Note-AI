package com.omkar.smartnotes.service.impl;

import com.omkar.smartnotes.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String summarize(String noteContent) {

        if (noteContent == null || noteContent.isBlank()) {
            return "No content to summarize";
        }

        try {
            return chatClient
                    .prompt()
                    .system("""
                        You are an expert note summarizer.
                        Always summarize the user's input clearly.
                        If input is short, still extract meaning and expand logically.
                        Output 3-5 bullet points only.
                    """)
                    .user("""
                        Summarize this note:

                        %s
                    """.formatted(noteContent))
                    .call()
                    .content();

        } catch (Exception e) {
            return "AI error: " + e.getMessage();
        }
    }
}