package com.jobly_jobs.service;

import com.jobly_jobs.client.OpenAiClient;
import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class VacancyTextService {
    private final OpenAiClient openAiClient;

    public GeneratedVacancyDto generatedVacancyText(JobCreationRequestDto inputDto) {
        Map<String, String> vacancyTextMap = new HashMap<>();
        new Thread(() -> vacancyTextMap.put("auto", testService("what is a car in 1 sentence?"))).start();
        new Thread(() -> vacancyTextMap.put("job", testService("what is a job in 1 sentence?"))).start();
        new Thread(() -> vacancyTextMap.put("company", testService("what is a company in 1 sentence?"))).start();

        while(vacancyTextMap.size() < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("Thread interrupted", e);
            }
        }
        System.out.println(vacancyTextMap);


        return GeneratedVacancyDto.builder()
                .summary("here is a nice summary")
                .aboutTheCompany(" fun company to work for")
                .team("team is amazing")
                .tasks("You gonna do all those things")
                .jobDescription("really challenging")
                .jobOffer("woooooo that's a nice offer")
                .build();
    }

    public String testService(String message) {
        log.debug("Testing testService with message: {}", message);
        return openAiClient.getResponse(message);
    }


}
