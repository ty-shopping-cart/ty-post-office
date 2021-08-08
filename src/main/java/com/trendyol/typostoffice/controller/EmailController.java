package com.trendyol.typostoffice.controller;

import com.trendyol.typostoffice.dto.EmailDto;
import com.trendyol.typostoffice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("email")
public class EmailController {
    
    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/")
    public ResponseEntity<String> sendMail (@RequestBody EmailDto emailDto) {
        return ResponseEntity.ok(emailService.sendMail(emailDto));
    }
}
