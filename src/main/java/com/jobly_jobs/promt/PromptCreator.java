package com.jobly_jobs.promt;

import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.domain.enums.WritingStyle;
import org.springframework.stereotype.Component;

@Component
public class PromptCreator {

    public String createPrompt(JobCreationRequestDto inputDto) {
        String genericContext = createBasicPrompt(inputDto);
        StringBuilder sb = new StringBuilder();
        sb.append(genericContext);
        sb.append(createCompanyDescription(inputDto));
        sb.append(createTeamDescription(inputDto));
        sb.append(createDateToDayDescription(inputDto));
        sb.append(createJobDescription(inputDto));
        sb.append(createJobUniqueSellingPoints(inputDto));
        sb.append(createRequirements(inputDto));
        sb.append(createOffer(inputDto));
        sb.append(createContactInformation(inputDto));
        sb.append(createSummary(inputDto));
        return sb.toString();
    }

    private String createBasicPrompt(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Act like an experienced recruiter that has great vacancy writing skills. Your task is to write a good vacancy" +
                "text that is appealing to applicants who have the job title you try to attract. " +
                "As an experienced you understand the job market,  know a lot about SEO and " +
                "how consumer psychology works. You return the vacancy text in a structured data format. This format contains " +
                " The following elements: " +
                "1. Summary: a short summary of the job, this summary applies the AIDA model and has the goal to grabs someonse attention. " +
                " and make someone interested to keep on reading. " +
                "2. Company description: a short description of the company. Explain here what they do and why they do it" +
                "3. Team description: a short description of the team. Explain what the team looks like and what makes it interesting to work with them" +
                "4. Day to day description: a short description of the day to day activities. Explain what the job looks like on a daily basis" +
                " and what challenges and opportunities are." +
                "5. Job description: a short description of the job. Explain what the job is about and what makes it interesting to do." +
                "6. Job unique selling points: a short description of the unique selling points of the job. Explain what makes this job unique and interesting." +
                "7. Requirements: a short description of the requirements for the job. Explain what the company is looking for in a candidate." +
                "This list should be between 4 and 6 requirements. Where the most important skills are listed first." +
                "8. Offer: a list description of the offer for the job. Explain what the company has to offer to the candidate." +
                "This list should be between 5 and 7 job benefits. Where the most important offers are listed first." +
                "9. Contact information: a short description of the contact information for the job. Explain how to apply for the job." +
                "Keep this structure in mind while writing the vacacytext and make sure there are NOT any repetitions in the text. " +
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
        sb.append("Keep the text fairly easy to read and understand. Use short sentences and paragraphs. Aim to write on B2 or C1 level." +
                "Make sure the text is unique and not copied from other sources. Also make sure the text is about 1 A4 long. ");
        return sb.toString();

    }

    private String createCompanyDescription(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("In this case write a company description and consider this " +
                "information as most important: the company is: ");
        sb.append(inputDto.generalInfo().companyName());
        sb.append(" First act like as a human recruiter and search the internet if you can find information about this company. " +
                "Make sure you find a website that's up to date. And written in the language the vacancy text is written in." +
                " If you can't find information about the company, you can use information that you already know. " +
                "If you don't know this company DO NOT make up information. Don't write about the company culture or " +
                "values if you don't know them. If you do know feel add them in the text. If you don't know the company," +
                " you can write about the industry the company is in. you can this based on the summary of the job");
        sb.append(inputDto.jobSummary());
        sb.append(" and the team description: ");
        sb.append(inputDto.teamDescription());
        sb.append(" Do not write explicitly about the team and the job. This will be written in another part.");
        return sb.toString();
    }

    private String createTeamDescription(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
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

    private String createDateToDayDescription(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
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

    private String createJobDescription(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
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

    private String createJobUniqueSellingPoints(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
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

    private String createRequirements(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
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

    private String createOffer(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
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

    private String createContactInformation(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("In this case write the contact information for the job and consider this " +
                "information as most important: the contact information is: :");
        sb.append(inputDto.generalInfo().companyName());
        sb.append(" make this an alinea of 2-3 sentences");
        return sb.toString();
    }

    private String createSummary(JobCreationRequestDto inputDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("In this case write a summary for the job and consider this " +
                "information as most important: the summary is: :");
        sb.append(inputDto.jobSummary());
        sb.append(" make this an alinea of 2-3 sentences. But also use the information that is written before.");
        return sb.toString();
    }


    private String writingStyleExample(WritingStyle style) {
        return switch (style) {
            case FORMAL ->
                    "You have to write in a formal style. This means you have to use formal language and avoid using contractions. For example, instead of writing 'you're' you have to write 'you are'.";
            case BUSINESS_CASUAL ->
                    "You have to write in a business casual style. This means you have to use a friendly tone and avoid using jargon. For example, instead of writing 'synergy' you have to write 'working together'.";
            case CASUAL ->
                    "You have to write in a casual style. This means you have to use a conversational tone and avoid using complex words. For example, instead of writing 'utilize' you have to write 'use'.";
            case CREATIVE ->
                    "You have to write in a creative style. This means you have to use imaginative language and avoid using cliches. For example, instead of writing 'think outside the box' you have to write 'be innovative'.";
            case TECHNICAL ->
                    "You have to write in a technical style. This means you have to use precise language and avoid using vague terms. For example, instead of writing 'a lot' you have to write 'many'. Focus on technical terms and inovative solutions.";
            default ->
                    "Write in a style that is easy to read and understand. Use short sentences and paragraphs. Aim to write on B2 or C1 level.";
        };
    }
}
