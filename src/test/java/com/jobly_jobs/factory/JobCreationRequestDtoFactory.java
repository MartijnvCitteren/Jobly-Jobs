package com.jobly_jobs.factory;

import com.jobly_jobs.domain.dto.request.JobDescriptionInputDto;

public class JobCreationRequestDtoFactory {
    public static JobDescriptionInputDto.JobDescriptionInputDtoBuilder createJobDescriptionInputDto() {
        return JobDescriptionInputDto.builder()
                .jobSummary("This is a job summary")
                .tasks("These are the tasks")
                .skills("These are the skills")
                .teamDescription("This is the team description");

    }
}
