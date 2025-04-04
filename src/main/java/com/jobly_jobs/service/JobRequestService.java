package com.jobly_jobs.service;

import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.dto.response.GeneratedVacancyDto;
import com.jobly_jobs.domain.dto.response.JobCreationResponseDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import com.jobly_jobs.domain.mapper.JobCreationMapper;
import com.jobly_jobs.domain.mapper.VacancyTextMapper;
import com.jobly_jobs.exceptions.JobRequestAlreadyExists;
import com.jobly_jobs.repository.JobCreationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class JobRequestService {
    private final JobCreationRepository jobCreationRepository;

    @Transactional
    public void createJobRequest(GeneralJobDescriptionInfoDto jobInfo, GeneratedVacancyDto vacancyDto) {
        try {
            if (isUniqueJobRequest(jobInfo)) {
                JobCreationRequest jobCreationRequest = JobCreationMapper.toNewJobCreationRequest(jobInfo);
                jobCreationRequest.setVacancyText(VacancyTextMapper.toVacancyText(vacancyDto));
                jobCreationRepository.save(jobCreationRequest);
            }

        } catch (DataAccessException e) {
            log.error("Error while saving job creation request for job: {} and company {}", jobInfo.jobTitle(),
                      jobInfo.companyName());
            throw e;
        }
    }

    @Transactional
    public JobCreationResponseDto getJobRequest(long id) {
        System.out.println("get Job request with ID " + id + " Started");
        try {
            JobCreationRequest jobCreationRequest = jobCreationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Job creation request with id: " + id + " not " + "found"));
            return JobCreationMapper.toJobCreationResponseDto(jobCreationRequest);
        } catch (DataAccessException e) {
            log.error("Error while fetching job creation request with id: {}", id);
            throw e;
        }
    }

    private boolean isUniqueJobRequest(GeneralJobDescriptionInfoDto jobInfo) {
        if (findSimilarJobRequest(jobInfo).isPresent()) {
            JobCreationRequest foundJobRequest = findSimilarJobRequest(jobInfo).get();
            String message = "This job creation request looks similar to a recent vacancy creation. " + "The similar "
                    + "request has job creation id: " + foundJobRequest.getId() + ".";
            log.info(message);
            throw new JobRequestAlreadyExists(message);
        }
        return true;
    }

    private Optional<JobCreationRequest> findSimilarJobRequest(GeneralJobDescriptionInfoDto jobInfo) {
        return jobCreationRepository.findByJobTitleAndFunctionGroupAndCompanyNameAndCreationDateAfter(
                jobInfo.jobTitle(), jobInfo.functionGroup(), jobInfo.companyName(), LocalDateTime.now().minusWeeks(2));
    }
}
