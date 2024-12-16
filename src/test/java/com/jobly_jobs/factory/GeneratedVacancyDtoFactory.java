package com.jobly_jobs.factory;

import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;

public class GeneratedVacancyDtoFactory {
    public static GeneratedVacancyDto.GeneratedVacancyDtoBuilder createGeneratedVacancyDto() {
        return GeneratedVacancyDto.builder()
                .summary("This is a job summary")
                .jobDescription("This is the job description")
                .tasks("These are the tasks")
                .team("This is the team")
                .jobOffer("This is the job offer")
                .aboutTheCompany("This is about the company");

    }
}
