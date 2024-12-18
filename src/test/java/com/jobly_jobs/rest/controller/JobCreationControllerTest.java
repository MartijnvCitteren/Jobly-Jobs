package com.jobly_jobs.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jobly_jobs.domain.dto.request.GeneralJobDescriptionInfoDto;
import com.jobly_jobs.domain.dto.request.JobCreationRequestDto;
import com.jobly_jobs.factory.GeneralJobInfoDtoFactory;
import com.jobly_jobs.factory.JobCreationRequestDtoFactory;
import com.jobly_jobs.service.JobRequestService;
import com.jobly_jobs.service.VacancyTextService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobCreationController.class)
@AutoConfigureMockMvc
class JobCreationControllerTest {

    @MockitoBean
    VacancyTextService vacancyService;
    @MockitoBean
    JobRequestService jobRequestService;
    @InjectMocks
    JobCreationController jobCreationController;
    @Autowired
    MockMvc mockMvc;

    @Test
    void givenCorrectInput_whenCreate_thenReturnsStatusCreated() throws Exception {
        // given
        GeneralJobDescriptionInfoDto generalInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();
        JobCreationRequestDto jobCreationRequestDto = JobCreationRequestDtoFactory.createJobDescriptionInputDto()
                .generalInfo(generalInfo)
                .build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/create")
                        .contentType("application/json")
                        .content(convertToJsonString(jobCreationRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void givenRequestWithoutGeneralInfo_whenCreate_thenReturnsStatusIsBadRequest() throws Exception {
        // given
        JobCreationRequestDto jobCreationRequestDto = JobCreationRequestDtoFactory.createJobDescriptionInputDto()
                .generalInfo(null)
                .build();

        //when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/create")
                        .contentType("application/json")
                        .content(convertToJsonString(jobCreationRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"generalInfo\":\"must not be null\"}"));
    }


    private String convertToJsonString(Object object) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return objectWriter.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}