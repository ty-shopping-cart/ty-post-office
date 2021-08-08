package com.trendyol.typostoffice.service.impl;

import com.trendyol.typostoffice.dto.NotificationDTO;
import com.trendyol.typostoffice.model.Notification;
import com.trendyol.typostoffice.model.CustomerNotification;
import com.trendyol.typostoffice.repository.NotificationRepository;
import com.trendyol.typostoffice.repository.CustomerNotificationRepository;
import com.trendyol.typostoffice.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class PostOfficeServiceImpl implements PostOfficeService {

    private NotificationRepository notificationRepository;

    private CustomerNotificationRepository customerNotificationRepository;

    @Autowired
    public PostOfficeServiceImpl(NotificationRepository notificationRepository,
                                 CustomerNotificationRepository customerNotificationRepository) {
        this.notificationRepository = notificationRepository;
        this.customerNotificationRepository = customerNotificationRepository;
    }

    @Override
    public String addNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setSubject(notificationDTO.getSubject());
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());

        Notification insertedNotification = notificationRepository.insert(notification);

        var customerNotification = customerNotificationRepository.findById(notificationDTO.getCustomerId());

        if(customerNotification.isPresent()){
            setNotificationsByCustomerId(customerNotification.get(), insertedNotification.getId());
        }
        else{
            createNotificationsByCustomerId(notificationDTO.getCustomerId(), insertedNotification.getId());
        }

        return "Notification is added";
    }


    private void setNotificationsByCustomerId(CustomerNotification customerNotification, String id) {
        customerNotification.getNotificationIds().add(id);
        customerNotificationRepository.insert(customerNotification);
    }

    private void createNotificationsByCustomerId(long customerId, String id) {
        var notificationByCustomerId = new CustomerNotification();
        notificationByCustomerId.setCustomerId(customerId);
        var notificationIds = new HashSet<String>();
        notificationIds.add(id);
        notificationByCustomerId.setNotificationIds(notificationIds);
        customerNotificationRepository.insert(notificationByCustomerId);
    }
}
