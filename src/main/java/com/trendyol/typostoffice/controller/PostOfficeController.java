package com.trendyol.typostoffice.controller;

import com.trendyol.typostoffice.dto.NotificationDTO;
import com.trendyol.typostoffice.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("postOffice")
public class PostOfficeController {

    private PostOfficeService postOfficeService;

    @Autowired
    public PostOfficeController(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addNotification (@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(postOfficeService.addNotification(notificationDTO));
    }
}
