package com.perfulandia.notificationservice.service;

import com.perfulandia.notificationservice.model.Notification;
import com.perfulandia.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> listar() {
        return notificationRepository.findAll();
    }

    public Notification guardar(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification buscarPorId(long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public void eliminar(long id) {
        notificationRepository.deleteById(id);
    }
}
