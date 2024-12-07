package com.jobly_jobs.domain.dto.response;

import lombok.Builder;

@Builder
public record GeneratedVacancyDto(
        String summary,
        String jobDescription,
        String tasks,
        String team,
        String jobOffer,
        String aboutTheCompany
) {
}
