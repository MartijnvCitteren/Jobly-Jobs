package com.jobly_jobs.domain.dto.request;

import com.jobly_jobs.domain.enums.FunctionGroup;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GeneralJobDescriptionInfoDto(
        String jobTitle,
        FunctionGroup functionGroup,
        String companyName,
        BigDecimal minSalary,
        BigDecimal maxSalary
) {
}
