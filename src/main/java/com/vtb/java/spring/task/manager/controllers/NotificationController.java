package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Notification;
import com.vtb.java.spring.task.manager.entities.dto.NotificationDto;
import com.vtb.java.spring.task.manager.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private NotificationService notificationService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification createNewNotification(@RequestBody Notification  notification) {
        if (notification.getId() != null) {
            notification.setId(null);
        }
        return notificationService.saveOrUpdate(notification);
    }

    @GetMapping("/show")
    public List<NotificationDto> findAllCommentariesDtoByTaskId(Principal principal) {
        String username = principal.getName();
        return notificationService.findAllNotificationsByUserName(username);
    }
}
