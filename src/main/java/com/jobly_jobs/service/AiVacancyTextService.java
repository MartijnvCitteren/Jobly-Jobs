package com.jobly_jobs.service;

import com.jobly_jobs.domain.dto.request.JobDescriptionInputDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AiVacancyTextService {
    public GeneratedVacancyDto generatedVacancyText(JobDescriptionInputDto inputDto) {
        log.atDebug().log("Generating vacancy text for job description input: {}", inputDto);

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
