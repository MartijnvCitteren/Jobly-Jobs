package com.jobly_jobs.rest.controller;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.service.JobRequestService;
import com.jobly_jobs.service.VacancyTextService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class JobCreationController {
    private final VacancyTextService aiVacancyService;
    private final JobRequestService jobRequestService;

    @PostMapping("/create")
    public ResponseEntity<GeneratedVacancyDto> generateVacancyText(@RequestBody @Valid JobCreationRequestDto descriptionInputDto) {
        jobRequestService.createJobRequest(descriptionInputDto.generalInfo());
        GeneratedVacancyDto generatedVacancy = aiVacancyService.generatedVacancyText(descriptionInputDto);
        return new ResponseEntity<>(generatedVacancy, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public String test() {
        return aiVacancyService.testService("explain what a job is in one sentence");
    }


}
