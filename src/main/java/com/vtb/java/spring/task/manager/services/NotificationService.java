package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Notification;
import com.vtb.java.spring.task.manager.entities.dto.NotificationDto;
import com.vtb.java.spring.task.manager.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;

    public Notification saveOrUpdate(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<NotificationDto> findAllNotificationsByUserName(String username){
        return notificationRepository.findAllNotificationsByUserName(username);
    }
}
