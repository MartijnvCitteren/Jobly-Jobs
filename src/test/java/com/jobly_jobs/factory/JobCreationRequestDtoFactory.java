package com.jobly_jobs.factory;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.enums.WritingStyle;

public class JobCreationRequestDtoFactory {
    public static JobCreationRequestDto.JobCreationRequestDtoBuilder createJobDescriptionInputDto() {
        return JobCreationRequestDto.builder()
                .jobSummary("This is a job summary")
                .tasks("These are the tasks")
                .skills("These are the skills")
                .teamDescription("This is the team description")
                .writingStyle(WritingStyle.CASUAL);

    }
}
