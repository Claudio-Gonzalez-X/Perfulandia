package com.perfulandia.notificationservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId; // ID del usuario que recibe la notificación
    private String mensaje; // Mensaje de la notificación
    private boolean leido; // Estado de lectura de la notificación
}
   