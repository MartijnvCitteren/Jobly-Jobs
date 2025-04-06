package com.jobly_jobs.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jobly_jobs.facade.JobCreationFacade;
import com.jobly_jobs.factory.GeneralJobInfoDtoFactory;
import com.jobly_jobs.factory.GeneratedVacancyDtoFactory;
import com.jobly_jobs.factory.JobCreationRequestDtoFactory;
import com.jobly_jobs.service.JobRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobCreationController.class)
@AutoConfigureMockMvc
class JobCreationControllerTest {
    @InjectMocks
    JobCreationController jobCreationController;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private JobCreationFacade jobCreationFacade;

    @MockitoBean
    private JobRequestService jobRequestService;

    @Test
    void givenCorrectInput_whenCreate_thenReturnsStatusCreated() throws Exception {
        // given
        var generalInfo = GeneralJobInfoDtoFactory.createGeneralInfoDto().build();
        var creationRequest = JobCreationRequestDtoFactory.createJobDescriptionInputDto()
                .generalInfo(generalInfo)
                .build();
        var generatedVacancyText = GeneratedVacancyDtoFactory.createGeneratedVacancyDto().build();
        when(jobCreationFacade.generateVacancyText(creationRequest)).thenReturn(generatedVacancyText);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/create/")
                                .contentType("application/json")
                                .content(convertToJsonString(creationRequest))).andExpect(status().isCreated());
        verify(jobCreationFacade, times(1)).generateVacancyText(creationRequest);
    }

    @Test
    void givenRequestWithoutGeneralInfo_whenCreate_thenReturnsStatusIsBadRequest() throws Exception {
        // given
        var jobCreationRequestDto = JobCreationRequestDtoFactory.createJobDescriptionInputDto()
                .generalInfo(null)
                .build();

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/create/")
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