package com.trendyol.typostoffice.dto;

import com.trendyol.typostoffice.domain.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long customerId;
    private String message;
    private String subject;
    private NotificationType type;
}
