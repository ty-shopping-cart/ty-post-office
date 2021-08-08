package com.trendyol.typostoffice.dto;

import com.trendyol.typostoffice.domain.enums.UserNotificationType;
import lombok.Data;

import java.util.List;

@Data
public class UserMessageNotificationDto {
    private List<UserDto> userList;

    private UserNotificationType userNotificationType;
}
