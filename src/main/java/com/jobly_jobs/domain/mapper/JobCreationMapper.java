package com.jobly_jobs.domain.mapper;

import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.entity.JobCreationRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JobCreationMapper {

    public static JobCreationRequest mapToNewJobCreationRequest(GeneralJobDescriptionInfoDto jobInfo) {
        JobCreationRequest jobCreationRequest = new JobCreationRequest();
        jobCreationRequest.setJobTitle(jobInfo.jobTitle());
        jobCreationRequest.setFunctionGroup(jobInfo.functionGroup());
        jobCreationRequest.setCompanyName(jobInfo.companyName());
        jobCreationRequest.setMinSalary(jobInfo.minSalary());
        jobCreationRequest.setMaxSalary(jobInfo.maxSalary());
        return jobCreationRequest;
    }
}

