package com.jobly_jobs.domain.dto.request;

import lombok.Builder;

@Builder
public record JobDescriptionInputDto(
        String jobSummary,
        String tasks,
        String skills,
        String teamDescription,
        GeneralJobDescriptionInfo generalInfo

) {
}
