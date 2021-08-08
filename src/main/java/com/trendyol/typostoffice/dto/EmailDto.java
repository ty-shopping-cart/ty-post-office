package com.trendyol.typostoffice.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String subject;
    private String message;
    private String to;
}
