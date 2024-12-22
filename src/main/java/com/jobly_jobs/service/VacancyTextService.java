package com.jobly_jobs.service;

import com.jobly_jobs.client.OpenAiClient;
import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.entity.VacancyText;
import com.jobly_jobs.promt.PromtCreator;
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
    private final PromtCreator promtCreator;


    public GeneratedVacancyDto generatedVacancyText(JobCreationRequestDto inputDto) {
        Map<String, String> vacancyTextMap = createHashMapWithGeneratedContend(inputDto);
        return GeneratedVacancyDto.builder()
                .summary(vacancyTextMap.get("summary"))
                .companyDescription(vacancyTextMap.get("companyDescription"))
                .teamDescription(vacancyTextMap.get("teamDescription"))
                .dayToDayDescription(vacancyTextMap.get("dayToDayDescription"))
                .jobDescription(vacancyTextMap.get("jobDescription"))
                .jobUniqueSellingPoints(vacancyTextMap.get("jobUniqueSellingPoints"))
                .requirements(vacancyTextMap.get("requirements"))
                .offer(vacancyTextMap.get("offer"))
                .contactInformation(vacancyTextMap.get("contactInformation"))
                .build();
    }

    private Map<String, String> createHashMapWithGeneratedContend(JobCreationRequestDto input) {
        Map<String, String> vacancyMap = new HashMap<>();
        new Thread(() -> vacancyMap.put("companyDescription", generateText(promtCreator.createCompanyDescription(input)))).start();
        new Thread(() -> vacancyMap.put("teamDescription", generateText(promtCreator.createTeamDescription(input)))).start();
        new Thread(() -> vacancyMap.put("dayToDayDescription", generateText(promtCreator.createDayToDayDescription(input)))).start();
        new Thread(() -> vacancyMap.put("jobDescription", generateText(promtCreator.createJobDescription(input)))).start();
        new Thread(() -> vacancyMap.put("jobUniqueSellingPoints", generateText(promtCreator.createJobUniqueSellingPoints(input)))).start();
        new Thread(() -> vacancyMap.put("requirements", generateText(promtCreator.createRequirements(input)))).start();
        new Thread(() -> vacancyMap.put("offer", generateText(promtCreator.createOffer(input)))).start();
        new Thread(() -> vacancyMap.put("contactInformation", generateText(promtCreator.createContactInformation(input)))).start();
        new Thread(() -> vacancyMap.put("summary", generateText(promtCreator.createSummary(input)))).start();

        while(vacancyMap.size() < 9) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("Thread interrupted", e);
            }
        }
        return vacancyMap;
    }

    private String generateText(String prompt) {
        return openAiClient.getResponse(prompt);
    }


}
