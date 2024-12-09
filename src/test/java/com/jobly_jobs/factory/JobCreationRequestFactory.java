package com.jobly_jobs.factory;

import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.enums.FunctionGroup;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class JobCreationRequestFactory {

    public static JobCreationRequest.JobCreationRequestBuilder createJobCreationRequest() {
        return JobCreationRequest.builder()
                .jobCreationId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .jobTitle("Software Engineer")
                .functionGroup(FunctionGroup.valueOf("ENGINEERING"))
                .companyName("Google")
                .minSalary(BigDecimal.valueOf(100000))
                .maxSalary(BigDecimal.valueOf(150000));
    }


}