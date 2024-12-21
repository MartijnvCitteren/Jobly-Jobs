package com.jobly_jobs.promt;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PromtCreator {
    private static final String BASIC_PROMPT = "Act like an HR professional that is specialised in writing job vacancies " +
            "focus only on a specific part of the vacancy text.";

    public String createCompanyDescription(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write a company description and consider this " +
                "information as most important: the company name is: :" + inputDto.generalInfo().companyName() +
                " also consider this information: " + inputDto.tasks() + " and " + inputDto.skills() + " and " + inputDto.teamDescription();
    }

    public String createTeamDescription(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write a team description and consider this " +
                "information as most important: the team looks the following: :" + inputDto.teamDescription() +
                " also consider this information: " + inputDto.tasks() + " and " + inputDto.skills() +
                " and the comapy name is: " + inputDto.generalInfo().companyName();
    }

    public String createDayToDayDescription(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write a day to day description and consider this " +
                "information as most important: the day to day activities are: :" + inputDto.tasks() +
                " also consider this information: " + inputDto.skills() + " and " + inputDto.teamDescription() +
                " and the comapy name is: " + inputDto.generalInfo().companyName();
    }

    public String createJobDescription(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write a job description and consider this " +
                "information as most important: the job description is: :" + inputDto.tasks() +
                " also consider this information: " + inputDto.skills() + " and " + inputDto.teamDescription() +
                "this text is the body of the vacancy text so make it two alineas long" +
                " and the comapy name is: " + inputDto.generalInfo().companyName();
    }

    public String createJobUniqueSellingPoints(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write a job's unique selling points. There should a maximum of four USP's " +
                " and consider this " +
                "information as most important: the job unique selling points are: :" + inputDto.tasks() +
                " also consider this information: " + inputDto.skills() + " and " + inputDto.teamDescription() +
                inputDto.generalInfo() + "and the company name is: " + inputDto.generalInfo().companyName();

    }

    public String createRequirements(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write the requirements for the job and consider this " +
                "information as most important: the requirements are: :" + inputDto.skills() +
                " also consider this information: " + inputDto.tasks() + " and " + inputDto.teamDescription();
    }

    public String createOffer(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write the offer for the job and consider this " +
                "information as most important: the offer is: :" + inputDto.generalInfo().minSalary() + " to " + inputDto.generalInfo().maxSalary() +
                " also consider this information: " + inputDto.skills() + " and " + inputDto.teamDescription() +
                " and the company name is: " + inputDto.generalInfo().companyName();
    }

    public String createContactInformation(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write the contact information for the job and consider this " +
                "information as most important: the contact information is: :" + inputDto.generalInfo().companyName() +
                " make this an alinea of 2-3 sentences";
    }

    public String createSummary(JobCreationRequestDto inputDto) {
        return BASIC_PROMPT + " In this case write a summary for the job and consider this " +
                "information as most important: the summary is: :" + inputDto.jobSummary() +
                " make this an alinea of 2-3 sentences. But also use the following information: " + inputDto.tasks() +
                " and " + inputDto.skills() + " and " + inputDto.teamDescription();
    }




}
