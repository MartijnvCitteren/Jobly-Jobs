package com.jobly_jobs.domain.mapper;

import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.factory.GeneralJobInfoDtoFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobCreationMapperTest {

    @Test
    public void givenGeneralJobDescriptionInfoDto_whenMapToNewJobCreationRequest_thenReturnJobCreationRequest() {
        // Given
        GeneralJobDescriptionInfoDto jobInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();

        // When
        JobCreationRequest result = JobCreationMapper.mapToNewJobCreationRequest(jobInfo);

        // Then
        assertEquals(jobInfo.jobTitle(), result.getJobTitle());
        assertEquals(jobInfo.functionGroup(), result.getFunctionGroup());
        assertEquals(jobInfo.companyName(), result.getCompanyName());
        assertEquals(jobInfo.minSalary(), result.getMinSalary());
        assertEquals(jobInfo.maxSalary(), result.getMaxSalary());
    }



}