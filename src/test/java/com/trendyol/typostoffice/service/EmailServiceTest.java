package com.trendyol.typostoffice.service;

import com.trendyol.typostoffice.dto.EmailDto;
import com.trendyol.typostoffice.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    JavaMailSender javaMailSender;

    @InjectMocks
    EmailServiceImpl emailService;

    @Test
    void sendEmail_returnSuccess_whenEverythingIsOkay(){
        EmailDto emailDto = new EmailDto();
        emailDto.setTo("test@test.gmail");
        emailDto.setSubject("test");
        emailDto.setMessage("test");
        emailService.sendMail(emailDto);
        Mockito.verify(javaMailSender, Mockito.times(1)).send(any(SimpleMailMessage.class));
    }

}
