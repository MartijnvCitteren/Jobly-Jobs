package com.jobly_jobs.domain.dto.request;

import com.jobly_jobs.domain.enums.FunctionGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GeneralJobDescriptionInfoDto(
        @Size(min = 3, max = 50, message = "Job title must be between 3 and 50 " + "characters") String jobTitle,

        @Size(min = 1, max = 50, message = "Function group must be between 1 and " + "50 characters") FunctionGroup functionGroup,

        @Size(min = 1, max = 100, message = "Company name must be between 1 and " + "100 characters") String companyName,

        @NotNull(message = "Min salary must be provided") BigDecimal minSalary,

        @NotNull(message = "Max salary must be provided") BigDecimal maxSalary
) {
}
