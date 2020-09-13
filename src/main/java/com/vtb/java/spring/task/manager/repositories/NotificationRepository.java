package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.Notification;
import com.vtb.java.spring.task.manager.entities.dto.NotificationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select n.id as id, n.text as text, n.createdAt as createdAt from Notification n " +
            "join n.users u where u.username = :username")
    List<NotificationDto> findAllNotificationsByUserName(String username);
}
