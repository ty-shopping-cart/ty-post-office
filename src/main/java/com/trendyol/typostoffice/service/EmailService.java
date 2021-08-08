package com.trendyol.typostoffice.service;

import com.trendyol.typostoffice.dto.EmailDto;

public interface EmailService {
    String sendMail(EmailDto emailDto);
}
