package com.trendyol.typostoffice.service;

import com.trendyol.typostoffice.domain.enums.NotificationType;
import com.trendyol.typostoffice.dto.NotificationDTO;
import com.trendyol.typostoffice.model.CustomerNotification;
import com.trendyol.typostoffice.model.Notification;
import com.trendyol.typostoffice.repository.CustomerNotificationRepository;
import com.trendyol.typostoffice.repository.NotificationRepository;
import com.trendyol.typostoffice.service.impl.PostOfficeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostOfficeServiceTest {
    @Mock
    NotificationRepository notificationRepository;

    @Mock
    CustomerNotificationRepository customerNotificationRepository;

    @InjectMocks
    PostOfficeServiceImpl postOfficeService;

    @Test
    void addNotification_returnSuccess_whenCustomerNotificationIsPresent(){
        Notification notification = new Notification();
        notification.setSubject("notification");
        notification.setMessage("notification body");
        notification.setType(NotificationType.E);
        notification.setId("12");
        when(notificationRepository.insert(any(Notification.class))).thenReturn(notification);

        Set<String> notificationIds = new HashSet<>();
        notificationIds.add("145");

        CustomerNotification customerNotification = new CustomerNotification();
        customerNotification.setNotificationIds(notificationIds);
        customerNotification.setCustomerId(1L);
        when(customerNotificationRepository.findById(any(Long.class))).thenReturn(Optional.of(customerNotification));

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSubject("notification");
        notificationDTO.setMessage("notification body");
        notificationDTO.setType(NotificationType.E);
        notificationDTO.setCustomerId(123L);

        postOfficeService.addNotification(notificationDTO);
        verify(customerNotificationRepository,Mockito.times(1)).insert(any(CustomerNotification.class));
    }

    @Test
    void addNotification_returnSuccess_whenCustomerNotificationIsNotPresent(){
        Notification notification = new Notification();
        notification.setSubject("notification");
        notification.setMessage("notification body");
        notification.setType(NotificationType.E);
        notification.setId("12");
        when(notificationRepository.insert(any(Notification.class))).thenReturn(notification);

        when(customerNotificationRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSubject("notification");
        notificationDTO.setMessage("notification body");
        notificationDTO.setType(NotificationType.E);
        notificationDTO.setCustomerId(123L);
        postOfficeService.addNotification(notificationDTO);

        verify(customerNotificationRepository,Mockito.times(1)).insert(any(CustomerNotification.class));
    }
}
