package com.jobly_jobs.service;

import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.mapper.JobCreationMapper;
import com.jobly_jobs.exceptions.JobRequestAlreadyExists;
import com.jobly_jobs.repository.JobCreationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class JobRequestService {
    private final JobCreationRepository jobCreationRepository;

    public void createJobRequest(GeneralJobDescriptionInfoDto jobInfo) {
        try {
            if (jobRequestIsUnique(jobInfo)) {
                JobCreationRequest jobCreationRequest = JobCreationMapper.toNewJobCreationRequest(jobInfo);
                jobCreationRepository.save(jobCreationRequest);
            }
        } catch (DataAccessException e) {
            log.error("Error while saving job creation request for job: {} and company {}", jobInfo.jobTitle(),
                                                                                                jobInfo.companyName());
            throw e;
        }
    }

    private boolean jobRequestIsUnique(GeneralJobDescriptionInfoDto jobInfo) {
        if (findSimilarJobRequest(jobInfo).isPresent()) {
            JobCreationRequest foundJobRequest = findSimilarJobRequest(jobInfo).get();
            String message = "This job creation request looks similar to a recent vacancy creation. " +
                    "The similar request has job creation id: " + foundJobRequest.getJobCreationId().toString() + ".";
            log.info(message);
            throw new JobRequestAlreadyExists(message);
        }
        return true;
    }

    private Optional<JobCreationRequest> findSimilarJobRequest(GeneralJobDescriptionInfoDto jobInfo) {
        return jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndRequestDateAfter(jobInfo.jobTitle(),
                jobInfo.functionGroup(), jobInfo.companyName(), LocalDateTime.now().minusWeeks(2));
    }


}
