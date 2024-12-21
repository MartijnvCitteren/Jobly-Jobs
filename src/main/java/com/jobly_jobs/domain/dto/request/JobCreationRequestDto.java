package com.jobly_jobs.domain.dto.request;

import com.jobly_jobs.domain.enums.WritingStyle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record JobCreationRequestDto(
        @Size(min = 20, max = 300, message = "Summary must be between 20 and 300 characters")
        String jobSummary,
        @Size(min = 10, max = 300, message = "Tasks must be between 10 and 300 characters")
        String tasks,
        @Size(min = 10, max = 300, message = "Skills must be between 10 and 300 characters")
        String skills,
        @Size(min = 10, max = 300, message = "Team description must be between 10 and 300 characters")
        String teamDescription,
        @NotNull
        WritingStyle writingStyle,
        @NotNull
        GeneralJobDescriptionInfoDto generalInfo

) {
}
