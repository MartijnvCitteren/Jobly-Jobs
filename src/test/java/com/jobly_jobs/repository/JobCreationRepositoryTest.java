package com.jobly_jobs.repository;

import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.enums.FunctionGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class JobCreationRepositoryTest {

    @Autowired
    JobCreationRepository jobCreationRepository;

    JobCreationRequest newJobCreationRequest;

    @BeforeEach
    void setUp() {
        newJobCreationRequest = new JobCreationRequest("Software Engineer", FunctionGroup.ENGINEERING, "Google",
                                                       BigDecimal.valueOf(1500), BigDecimal.valueOf(2000),
                                                       LocalDateTime.now(), LocalDateTime.now());
        jobCreationRepository.save(newJobCreationRequest);
    }

    @AfterEach
    void tearDown() {
        jobCreationRepository.deleteAll();
    }

    @Test
    void givenGoodTestSetup_whenFindAll_theReturnListOfOne() {
        //when
        List<JobCreationRequest> result = jobCreationRepository.findAll();

        //then
        assertEquals(1, result.size());
    }

    @Test
    void given3similarRequest_whenFindingSimilarRequestsCreatedInTheLastDay_thenReturnOneRequest() {
        //given
        String jobTitle = "Software Engineer";
        FunctionGroup functionGroup = FunctionGroup.ENGINEERING;
        String companyName = "Google";
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);

        //when
        Optional<JobCreationRequest> result =
                jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndCreationDateAfter(
                jobTitle, functionGroup, companyName, createdAt);

        //then
        assertTrue(result.isPresent());
    }

}