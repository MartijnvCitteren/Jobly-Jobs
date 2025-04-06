package com.jobly_jobs.client;

import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;


@Service
@Log4j2
public class OpenAiClient {
    private final ChatClient chatClient;

    public OpenAiClient(ChatClient chatClient) {
        log.debug("OpenAiClient created");
        this.chatClient = chatClient;
    }

    public GeneratedVacancyDto getResponse(String message) {
        try {
            return chatClient.prompt().user(message).call().entity(GeneratedVacancyDto.class);
        } catch (RestClientException e) {
            log.error("Error while calling OpenAI API", e);
            throw e;
        }
    }


}
