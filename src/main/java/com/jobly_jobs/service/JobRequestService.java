package com.jobly_jobs.service;

import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.entity.VacancyText;
import com.jobly_jobs.domain.mapper.JobCreationMapper;
import com.jobly_jobs.repository.JobCreationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class JobRequestService {
    private final JobCreationRepository jobCreationRepository;

    public void createJobRequest(GeneralJobDescriptionInfoDto jobInfo) {
        JobCreationRequest jobCreationRequest = JobCreationMapper.mapToNewJobCreationRequest(jobInfo);
        try {
            jobCreationRepository.save(jobCreationRequest);
        } catch (DataAccessException e) {
            log.error("Error while saving job with job creation id: {} : {}", jobCreationRequest.getJobCreationId(), e.getMessage());
            throw e;
        }

    }


}
