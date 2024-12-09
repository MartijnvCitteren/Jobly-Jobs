package com.jobly_jobs.rest.controller;

import com.jobly_jobs.domain.dto.request.JobDescriptionInputDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.service.AiVacancyTextService;
import com.jobly_jobs.service.JobRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class JobCreationController {
    private final AiVacancyTextService aiVacancyService;
    private final JobRequestService jobRequestService;

    @PostMapping("/create")
    public ResponseEntity<GeneratedVacancyDto> generateVacancyText(@RequestBody JobDescriptionInputDto descriptionInputDto) {
        jobRequestService.createJobRequest(descriptionInputDto.generalInfo());
        GeneratedVacancyDto generatedVacancy = aiVacancyService.generatedVacancyText(descriptionInputDto);
        return new ResponseEntity<>(generatedVacancy, HttpStatus.CREATED);
    }


}
