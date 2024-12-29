package com.jobly_jobs.rest.controller;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.domain.dto.response.JobCreationResponseDto;
import com.jobly_jobs.service.JobRequestService;
import com.jobly_jobs.service.VacancyTextService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequiredArgsConstructor
public class JobCreationController {
    private final VacancyTextService aiVacancyService;
    private final JobRequestService jobRequestService;

    @PostMapping("/create/")
    public ResponseEntity<GeneratedVacancyDto> generateVacancyText(@RequestBody @Valid JobCreationRequestDto descriptionInputDto) {
        GeneratedVacancyDto generatedVacancy = aiVacancyService.generatedVacancyText(descriptionInputDto);
        jobRequestService.createJobRequest(descriptionInputDto.generalInfo(), generatedVacancy);
        return new ResponseEntity<>(generatedVacancy, HttpStatus.CREATED);
    }

    @GetMapping("/view/")
    public ResponseEntity<JobCreationResponseDto> viewVacancyRequest(@RequestParam long id) {
        JobCreationResponseDto jobCreationResponseDto = jobRequestService.getJobRequest(id);
        return new ResponseEntity<>(jobCreationResponseDto, HttpStatus.OK);
    }

}
