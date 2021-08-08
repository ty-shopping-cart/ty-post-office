package com.trendyol.typostoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.typostoffice.dto.EmailDto;
import com.trendyol.typostoffice.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmailControllerTest {

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Mock
    EmailService emailService;

    @BeforeEach
    void beforeEach(){
        if (mvc != null) {
            return;
        }

        this.mvc = MockMvcBuilders.standaloneSetup(new EmailController(emailService))
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void sendEmail_returnSuccess_whenEverythingIsOkay() throws Exception {
        EmailDto request = new EmailDto();
        request.setSubject("testSubject");
        request.setMessage("testMessage");
        request.setTo("test@gmail.com");

        String expected = "Email Gonderimi basarili";

        Mockito.when(emailService.sendMail(request)).thenReturn(expected);

        MvcResult mvcResult = mvc.perform(post("/email/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        assertThat(response).isEqualTo(expected);
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
