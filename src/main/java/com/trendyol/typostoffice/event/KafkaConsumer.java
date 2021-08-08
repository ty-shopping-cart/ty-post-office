package com.trendyol.typostoffice.event;

import com.trendyol.typostoffice.domain.message.UserMessage;
import com.trendyol.typostoffice.dto.UserMessageNotificationDto;
import com.trendyol.typostoffice.service.EmailService;
import com.trendyol.typostoffice.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {
    private PostOfficeService postOfficeService;
    private List<UserMessage> userMessageList;
    private EmailService emailService;

    @Autowired
    public KafkaConsumer(PostOfficeService postOfficeService, List<UserMessage> userMessageList, EmailService emailService) {
        this.postOfficeService = postOfficeService;
        this.userMessageList = userMessageList;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "UserNotificationMessage", groupId = "trendyol",
            containerFactory = "notificationKafkaListenerContainerFactory")
    public void consume(@Payload UserMessageNotificationDto message) {
        UserMessage userMessage = userMessageList.stream().filter(x -> x.isMatch(message.getUserNotificationType())).findFirst().orElse(null);
        message.getUserList().stream().forEach(x -> {
            postOfficeService.addNotification(userMessage.getNotification(x.getUserId()));
            emailService.sendMail(userMessage.getEmail(x.getEmail()));
        });
    }

}
