package com.omkar.smartnotes.service.impl;

import com.omkar.smartnotes.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String summarize(String noteContent) {

        return chatClient.prompt()
                .system("""
                        You are Smart Notes AI.

                        Analyze the note.

                        If it looks like:
                        - a topic
                        - technology
                        - framework
                        - concept

                        explain it.

                        If it looks like meeting notes,
                        summarize it.

                        If it looks like tasks,
                        extract action items.
                        """)
                .user(noteContent)
                .call()
                .content();
    }

    @Override
    public Flux<String> streamSummary(String noteContent) {

        return chatClient.prompt()
                .system("""
                        You are Smart Notes AI.

                        Stream the response progressively.

                        Use markdown formatting.
                        """)
                .user(noteContent)
                .stream()
                .content();
    }

    @Override
    public String explain(String topic) {

        return chatClient.prompt()
                .system("""
                        You are an expert software architect.

                        Explain topics deeply.

                        Include:
                        - Overview
                        - Architecture
                        - Features
                        - Advantages
                        - Example
                        - Interview Questions
                        """)
                .user(topic)
                .call()
                .content();
    }

    @Override
    public String improve(String note) {

        return chatClient.prompt()
                .system("""
                        You are a professional editor.

                        Improve the note.

                        Fix:
                        - grammar
                        - spelling
                        - structure
                        - readability

                        Keep original meaning.

                        Return improved version only.
                        """)
                .user(note)
                .call()
                .content();
    }

    @Override
    public String chat(String message) {

        return chatClient.prompt()
                .system("""
                        You are Smart Notes AI Assistant.

                        Answer clearly.

                        Use notes context when available.

                        Be concise but helpful.
                        """)
                .user(message)
                .call()
                .content();
    }
}