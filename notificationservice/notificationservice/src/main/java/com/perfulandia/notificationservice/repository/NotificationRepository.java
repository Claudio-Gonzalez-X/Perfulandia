package com.perfulandia.notificationservice.repository;

import com.perfulandia.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
