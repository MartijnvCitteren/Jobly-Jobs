package com.jobly_jobs.service;

import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.enums.FunctionGroup;
import com.jobly_jobs.exceptions.JobRequestAlreadyExists;
import com.jobly_jobs.factory.GeneralJobInfoDtoFactory;
import com.jobly_jobs.factory.GeneratedVacancyDtoFactory;
import com.jobly_jobs.repository.JobCreationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JobRequestServiceTest {

    @Mock
    private JobCreationRepository jobCreationRepository;

    @InjectMocks
    private JobRequestService jobRequestService;

    private GeneratedVacancyDto vacancyDto;

    @BeforeEach
    void setUp() {
        vacancyDto = GeneratedVacancyDtoFactory.createGeneratedVacancyDto().build();
    }


    @Test
    void givenAJobRequestIsUnique_whenTheJobRequestIsCreated_thenTheIsSavedJobRequest() {
        // Given
        GeneralJobDescriptionInfoDto jobInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();

        // When
        when(jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndCreationDateAfter(anyString(),
                                                                                                    any(FunctionGroup.class),
                                                                                                    anyString(),
                                                                                                    any(LocalDateTime.class))).thenReturn(
                Optional.empty());
        jobRequestService.createJobRequest(jobInfo, vacancyDto);

        // Then
        verify(jobCreationRepository, times(1)).save(any(JobCreationRequest.class));
    }

    @Test
    void givenAJobRequestIsNotUnique_whenTheJobRequestIsCreated_thenThrowJobRequestAlreadyExists() {
        // Given
        GeneralJobDescriptionInfoDto jobInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();
        JobCreationRequest existingJobRequest = new JobCreationRequest();

        // When & Then
        when(jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndCreationDateAfter(anyString(),
                                                                                                    any(FunctionGroup.class),
                                                                                                    anyString(),
                                                                                                    any(LocalDateTime.class))).thenReturn(
                Optional.of(existingJobRequest));
        JobRequestAlreadyExists exception = assertThrows(JobRequestAlreadyExists.class,
                                                         () -> jobRequestService.createJobRequest(jobInfo, vacancyDto));

        assertTrue(exception.getMessage()
                           .contains("This job creation request looks similar to a recent vacancy creation."));
        verify(jobCreationRepository, never()).save(any(JobCreationRequest.class));
    }

    // add test where you throw a data access exception
    @Test
    void givenADataAccessException_whenTheJobRequestIsCreated_thenThrowDataAccessException() {
        // Given
        DataAccessException dataAccessException = Mockito.mock(DataAccessException.class);
        GeneralJobDescriptionInfoDto jobInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();

        // When & then
        when(jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndCreationDateAfter(anyString(),
                                                                                                    any(FunctionGroup.class),
                                                                                                    anyString(),
                                                                                                    any(LocalDateTime.class))).thenThrow(
                dataAccessException);
        assertThrows(DataAccessException.class, () -> jobRequestService.createJobRequest(jobInfo, vacancyDto));
    }

}