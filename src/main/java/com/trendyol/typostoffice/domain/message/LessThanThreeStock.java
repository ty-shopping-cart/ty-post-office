package com.trendyol.typostoffice.domain.message;

import com.trendyol.typostoffice.constant.MessageConstant;
import com.trendyol.typostoffice.domain.enums.NotificationType;
import com.trendyol.typostoffice.domain.enums.UserNotificationType;
import com.trendyol.typostoffice.dto.EmailDto;
import com.trendyol.typostoffice.dto.NotificationDTO;
import org.springframework.stereotype.Component;

@Component
public class LessThanThreeStock implements UserMessage {
    @Override
    public boolean isMatch(UserNotificationType userNotificationType) {
        return userNotificationType == UserNotificationType.LESSTHANTHREE;
    }

    @Override
    public NotificationDTO getNotification(Integer userId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setCustomerId(userId.longValue());
        notificationDTO.setMessage(MessageConstant.LESSTHANTHREESTOCKMESSAGE);
        notificationDTO.setSubject(MessageConstant.STOCKSUBJECTMESSAGE);
        notificationDTO.setType(NotificationType.E);

        return notificationDTO;
    }

    @Override
    public EmailDto getEmail(String email) {
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(email);
        emailDto.setMessage(MessageConstant.LESSTHANTHREESTOCKMESSAGE);
        emailDto.setSubject(MessageConstant.STOCKSUBJECTMESSAGE);

        return emailDto;
    }
}
