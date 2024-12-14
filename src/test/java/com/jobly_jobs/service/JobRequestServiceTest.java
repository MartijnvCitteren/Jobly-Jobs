package com.jobly_jobs.service;

import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.enums.FunctionGroup;
import com.jobly_jobs.exceptions.JobRequestAlreadyExists;
import com.jobly_jobs.factory.GeneralJobInfoDtoFactory;
import com.jobly_jobs.repository.JobCreationRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class JobRequestServiceTest {

    @Mock
    JobCreationRepository jobCreationRepository;

    @InjectMocks
    JobRequestService jobRequestService;

    @Test
    public void givenAJobRequestIsUnique_whenTheJobRequestIsCreated_thenTheJobRequestIsSaved() {
    // Given
    GeneralJobDescriptionInfoDto jobInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();

    // When
    when(jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndRequestDateAfter(
                anyString(), any(FunctionGroup.class), anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());
    jobRequestService.createJobRequest(jobInfo);

    // Then
    verify(jobCreationRepository, times(1)).save(any(JobCreationRequest.class));
}

    @Test
    public void givenAJobRequestIsNotUnique_whenTheJobRequestIsCreated_thenThrowJobRequestAlreadyExists() {
        // Given
        GeneralJobDescriptionInfoDto jobInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();
        JobCreationRequest existingJobRequest = new JobCreationRequest();

        // When & Then
        when(jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndRequestDateAfter(
                anyString(), any(FunctionGroup.class), anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(existingJobRequest));
        JobRequestAlreadyExists exception = assertThrows(JobRequestAlreadyExists.class, () -> {
            jobRequestService.createJobRequest(jobInfo);
        });

        assertTrue(exception.getMessage().contains("This job creation request looks similar to a recent vacancy creation."));
        verify(jobCreationRepository, never()).save(any(JobCreationRequest.class));
}



}