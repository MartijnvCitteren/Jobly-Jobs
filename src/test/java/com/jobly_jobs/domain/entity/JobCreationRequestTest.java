package com.jobly_jobs.domain.entity;

import com.jobly_jobs.factory.JobCreationRequestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobCreationRequestTest {

    @Test
    void givenNewJobCreationRequest_whenCreated_thenJobCreationIdIsNotNull() {
        //given & when
        JobCreationRequest jobCreationRequest = new JobCreationRequest();

        //then
        assertNotNull(jobCreationRequest.getJobCreationId());
    }

    @Test
    void givenExistingJobCreationRequest_whenGetAnyValue_thenValueIsReturned() {
        //given
        JobCreationRequest jobCreationRequest = JobCreationRequestFactory.createJobCreationRequest().build();

        //when
        String jobTitle = jobCreationRequest.getJobTitle();

        //then
        assertEquals("Software Engineer", jobTitle);
        assertNotNull(jobCreationRequest.getJobCreationId());
        assertNotNull(jobCreationRequest.getFunctionGroup());
        assertNotNull(jobCreationRequest.getCompanyName());
        assertNotNull(jobCreationRequest.getMinSalary());
        assertNotNull(jobCreationRequest.getMaxSalary());
    }

}