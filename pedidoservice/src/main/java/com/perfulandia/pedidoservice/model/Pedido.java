package com.perfulandia.pedidoservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId; // ID del usuario que realiza el pedido
    private double total; // Total del pedido
    private String estado; // Estado del pedido (ej. "en proceso", "enviado", "entregado")
}
