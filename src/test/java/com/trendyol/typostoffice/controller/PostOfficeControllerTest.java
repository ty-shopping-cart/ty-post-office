package com.trendyol.typostoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.typostoffice.domain.enums.NotificationType;
import com.trendyol.typostoffice.dto.EmailDto;
import com.trendyol.typostoffice.dto.NotificationDTO;
import com.trendyol.typostoffice.service.EmailService;
import com.trendyol.typostoffice.service.PostOfficeService;
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
public class PostOfficeControllerTest {

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Mock
    PostOfficeService postOfficeService;

    @BeforeEach
    void beforeEach(){
        if (mvc != null) {
            return;
        }

        this.mvc = MockMvcBuilders.standaloneSetup(new PostOfficeController(postOfficeService))
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void addNotification_returnSuccess_whenEverythingIsOkay() throws Exception {
        NotificationDTO request = new NotificationDTO();
        request.setSubject("testSubject");
        request.setMessage("testMessage");
        request.setType(NotificationType.E);
        request.setCustomerId(1L);

        String expected = "Notification is added";

        Mockito.when(postOfficeService.addNotification(request)).thenReturn(expected);

        MvcResult mvcResult = mvc.perform(post("/postOffice/")
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
