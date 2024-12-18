package com.jobly_jobs.client;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class OpenAiClient {
    private final ChatClient chatClient;

    public OpenAiClient(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("excute my question exactly")
                .build();
    }

    public String getResponse(String message) {
        return chatClient.prompt(message)
                .user(message)
                .call()
                .content();
    }



}
