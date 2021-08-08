package com.trendyol.typostoffice.domain.message;

import com.trendyol.typostoffice.domain.enums.UserNotificationType;
import com.trendyol.typostoffice.dto.EmailDto;
import com.trendyol.typostoffice.dto.NotificationDTO;

public interface UserMessage {

    boolean isMatch(UserNotificationType userNotificationType);

    NotificationDTO getNotification(Integer userId);

    EmailDto getEmail(String email);
}
