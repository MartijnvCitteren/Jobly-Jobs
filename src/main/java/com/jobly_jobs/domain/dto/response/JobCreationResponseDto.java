package com.jobly_jobs.domain.dto.response;

import com.jobly_jobs.domain.entity.VacancyText;
import com.jobly_jobs.domain.enums.FunctionGroup;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record JobCreationResponseDto(
    String jobTitle,
    FunctionGroup functionGroup,
    String companyName,
    BigDecimal minSalary,
    BigDecimal maxSalary,
    VacancyText vacancyText
) {}
