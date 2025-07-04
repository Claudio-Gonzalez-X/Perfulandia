package com.perfulandia.carritoservice.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productoId; // ID del producto
    private int cantidad; // Cantidad del producto en el carrito
}
