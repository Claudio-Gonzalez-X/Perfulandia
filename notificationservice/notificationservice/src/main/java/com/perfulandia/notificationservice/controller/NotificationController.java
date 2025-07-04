package com.perfulandia.notificationservice.controller;

import com.perfulandia.notificationservice.model.Notification;
import com.perfulandia.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notification> listar() {
        return service.listar();
    }

    @PostMapping
    public Notification guardar(@RequestBody Notification notification) {
        return service.guardar(notification);
    }

    @GetMapping("/{id}")
    public Notification buscar(@PathVariable long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }
}