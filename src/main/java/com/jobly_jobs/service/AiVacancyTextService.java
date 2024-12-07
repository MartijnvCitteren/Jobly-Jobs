package com.jobly_jobs.service;

import com.jobly_jobs.domain.dto.request.JobDescriptionInputDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import org.springframework.stereotype.Service;

@Service
public class AiVacancyTextService {
    public GeneratedVacancyDto generatedVacancyText(JobDescriptionInputDto inputDto) {
        System.out.println("Normally I would generate an AI text and use " + inputDto);

        return GeneratedVacancyDto.builder()
                .summary("here is a nice summary")
                .aboutTheCompany(" fun company to work for")
                .team("team is amazing")
                .tasks("You gonna do all those things")
                .jobDescription("really challenging")
                .jobOffer("woooooo that's a nice offer")
                .build();
    }
}
