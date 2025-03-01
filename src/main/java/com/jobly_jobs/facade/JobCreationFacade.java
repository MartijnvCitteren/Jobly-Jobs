package com.jobly_jobs.facade;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.promt.PromptCreator;
import com.jobly_jobs.service.JobRequestService;
import com.jobly_jobs.service.VacancyTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JobCreationFacade {
    private final VacancyTextService aiVacancyService;
    private final JobRequestService jobRequestService;
    private final PromptCreator promptCreator;

    public GeneratedVacancyDto generateVacancyText(JobCreationRequestDto descriptionInputDto) {
        String prompt = promptCreator.createPrompt(descriptionInputDto);
        GeneratedVacancyDto generatedVacancy = aiVacancyService.generatedVacancyText(prompt);
        jobRequestService.createJobRequest(descriptionInputDto.generalInfo(), generatedVacancy);
        return generatedVacancy;
    }
}
