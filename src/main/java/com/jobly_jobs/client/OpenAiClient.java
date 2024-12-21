package com.jobly_jobs.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class OpenAiClient {
    private final ChatClient chatClient;

    public OpenAiClient(ChatClient chatClient) {
        log.debug("OpenAiClient created");
        this.chatClient = chatClient;
    }

    public String getResponse(String message) {
        ChatResponse response = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        return response.getResult().getOutput().getText();
    }



}
