package com.jobly_jobs.promt;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.entity.Prompt;
import com.jobly_jobs.domain.enums.WritingStyle;
import org.springframework.stereotype.Component;

@Component
public class PromptCreator {

//    public Prompt createPrompt(JobCreationRequestDto inputDto) {
//        String genericContext = createBasicPrompt(inputDto);
//        Prompt prompt = new Prompt();
//        prompt.setGenericContext(genericContext);
//        prompt.setCompanyDescription(createCompanyDescription(inputDto, genericContext));
//        prompt.setTeamDescription(createTeamDescription(inputDto, genericContext));
//        prompt.setDayToDayDescription(createDateToDayDescription(inputDto, genericContext));
//        prompt.setJobDescription(createJobDescription(inputDto, genericContext));
//        prompt.setJobUniqueSellingPoints(createJobUniqueSellingPoints(inputDto, genericContext));
//        prompt.setRequirements(createRequirements(inputDto, genericContext));
//        prompt.setOffer(createOffer(inputDto, genericContext));
//        prompt.setContactInformation(createContactInformation(inputDto, genericContext));
//        prompt.setSummary(createSummary(inputDto, genericContext));
//        return prompt;
//    }

    public String createPrompt(JobCreationRequestDto inputDto) {
        String genericContext = createBasicPrompt(inputDto);
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append(createCompanyDescription(inputDto, genericContext));
        sb.append(createTeamDescription(inputDto, genericContext));
        sb.append(createDateToDayDescription(inputDto, genericContext));
        sb.append(createJobDescription(inputDto, genericContext));
        sb.append(createJobUniqueSellingPoints(inputDto, genericContext));
        sb.append(createRequirements(inputDto, genericContext));
        sb.append(createOffer(inputDto, genericContext));
        sb.append(createContactInformation(inputDto, genericContext));
        sb.append(createSummary(inputDto, genericContext));
        return sb.toString();
    }

    private String createBasicPrompt(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Act like an experienced recruiter that has great writing skills. Your task is to write a good vacancy" +
                "text that is appealing. As a write you understand the job market,  know a lot about SEO and " +
                "how consumer psychology works. You only write a specific part of this job description. In this firs lines" +
                "you get some basic information about the job. If you don't think you need the information for the " +
                "specific part you are writing, you can ignore it. ");
        sb.append("There is specific information you need to use in this text. The company name is: ");
        sb.append(inputDto.generalInfo().companyName());
        sb.append(" and the job title is: ");
        sb.append(inputDto.generalInfo().jobTitle());
        sb.append("You have to write this vacancy text in this language: ");
        sb.append(inputDto.language());
        sb.append(" and in this writing style: ");
        sb.append(inputDto.writingStyle());
        sb.append(writingStyleExample(inputDto.writingStyle()));
        sb.append("Keep the text fairly easy to read and understand. Use short sentences and paragraphs. Aim to write on B2 or C1 level.");
        return sb.toString();

    }

    private String createCompanyDescription(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write a company description and consider this " +
                "information as most important: the company is: ");
        sb.append(inputDto.generalInfo().companyName());
        sb.append(" If you know what this company does, you can write about that. If you don't know this company DO NOT make up information.");
        sb.append(" Don't write about the company culture or values if you don't know them. If you know feel free to do so.");
        sb.append(" If you don't know the company, you can write about the industry the company is in. you can this based on the summary of the job: ");
        sb.append(inputDto.jobSummary());
        sb.append(" and the team description: ");
        sb.append(inputDto.teamDescription());
        sb.append(" Do not write explicitly about the team and the job. This will be written in another part.");
        return sb.toString();
    }

    private String createTeamDescription(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write a team description and consider this " +
                "information as most important: the team looks the following: :");
        sb.append(inputDto.teamDescription());
        sb.append(" also consider this information: ");
        sb.append(inputDto.tasks());
        sb.append(" and ");
        sb.append(inputDto.skills());
        sb.append(" Focus on this part really on the team. The skills and tasks are just there for some context.");
        return sb.toString();
    }

    private String createDateToDayDescription(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write a day to day description and consider this " +
                "information as most important: the day to day activities are: :");
        sb.append(inputDto.tasks());
        sb.append(" also consider this information: ");
        sb.append(inputDto.skills());
        sb.append(" and ");
        sb.append(inputDto.teamDescription());
        sb.append(" Focus on this part really on the day to day activities. The skills and team description are just there for some context.");
        return sb.toString();
    }

    private String createJobDescription(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write a job description and consider this " +
                "information as most important: the job description is: :");
        sb.append(inputDto.tasks());
        sb.append(" also consider this information: ");
        sb.append(inputDto.skills());
        sb.append(" and ");
        sb.append(inputDto.teamDescription());
        sb.append("this text is the body of the vacancy text so make it two alineas long");
        sb.append(" Focus on this part really on the job description. The skills and team description are just there for some context.");
        return sb.toString();
    }

    private String createJobUniqueSellingPoints(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write a job's unique selling points. There should a maximum of four USP's " +
                " and consider this " +
                "information as most important: the job unique selling points are: :");
        sb.append(inputDto.tasks());
        sb.append(" also consider this information: ");
        sb.append(inputDto.skills());
        sb.append(" and ");
        sb.append(inputDto.teamDescription());
        sb.append("focus on the USP's and make this text 2-3 alineas long");
        return sb.toString();
    }

    private String createRequirements(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write the requirements for the job and consider this " +
                "information as most important: the requirements are: :");
        sb.append(inputDto.skills());
        sb.append(" also consider this information: ");
        sb.append(inputDto.tasks());
        sb.append(" and ");
        sb.append(inputDto.teamDescription());
        sb.append("focus really on the requirements and make a bullet point list of the requirements "
                + "and order them in order of importance. Generaly speaking start with education or work experience");
        return sb.toString();
    }

    private String createOffer(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write the offer for the job and consider this " +
                "information as most important: the offer is: :");
        sb.append(inputDto.generalInfo().minSalary());
        sb.append(" to ");
        sb.append(inputDto.generalInfo().maxSalary());
        sb.append(" also consider this information: ");
        sb.append(inputDto.skills());
        sb.append(" and ");
        sb.append(inputDto.teamDescription());
        sb.append(" and the company name is: ");
        sb.append(inputDto.generalInfo().companyName());
        sb.append("really focus on the offer here, make a bullet point list of the offer and order them in order of importance");
        return sb.toString();
    }

    private String createContactInformation(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write the contact information for the job and consider this " +
                "information as most important: the contact information is: :");
        sb.append(inputDto.generalInfo().companyName());
        sb.append(" make this an alinea of 2-3 sentences");
        return sb.toString();
    }

    private String createSummary(JobCreationRequestDto inputDto, String genericContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append("In this case write a summary for the job and consider this " +
                "information as most important: the summary is: :");
        sb.append(inputDto.jobSummary());
        sb.append(" make this an alinea of 2-3 sentences. But also use the following information: ");
        sb.append(inputDto.tasks());
        sb.append(" and ");
        sb.append(inputDto.skills());
        sb.append(" and ");
        sb.append(inputDto.teamDescription());
        return sb.toString();
    }


    private String writingStyleExample(WritingStyle style) {
        switch (style) {
            case FORMAL:
                return "You have to write in a formal style. This means you have to use formal language and avoid using contractions. For example, instead of writing 'you're' you have to write 'you are'.";
            case BUSINESS_CASUAL:
                return "You have to write in a business casual style. This means you have to use a friendly tone and avoid using jargon. For example, instead of writing 'synergy' you have to write 'working together'.";
            case CASUAL:
                return "You have to write in a casual style. This means you have to use a conversational tone and avoid using complex words. For example, instead of writing 'utilize' you have to write 'use'.";
            case CREATIVE:
                return "You have to write in a creative style. This means you have to use imaginative language and avoid using cliches. For example, instead of writing 'think outside the box' you have to write 'be innovative'.";
            case TECHNICAL:
                return "You have to write in a technical style. This means you have to use precise language and avoid using vague terms. For example, instead of writing 'a lot' you have to write 'many'. Focus on technical terms and inovative solutions.";
            default:
                return "Write in a style that is easy to read and understand. Use short sentences and paragraphs. Aim to write on B2 or C1 level.";
        }
    }
}
