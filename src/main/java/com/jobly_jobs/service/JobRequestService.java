package com.jobly_jobs.service;

import com.jobly_jobs.domain.Entity.JobCreationRequest;
import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfo;
import com.jobly_jobs.domain.mapper.JobCreationMapper;
import com.jobly_jobs.repository.JobCreationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobRequestService {
    private final JobCreationRepository jobCreationRepository;

    public void createJobRequest(GeneralJobDescriptionInfo jobInfo) {
        try {
            JobCreationRequest jobCreationRequest = JobCreationMapper.mapToNewJobCreationRequest(jobInfo);
            jobCreationRepository.save(jobCreationRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating job request");

        }

    }

}
