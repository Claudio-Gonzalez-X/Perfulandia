package com.perfulandia.carritoservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long usuarioId; // ID del usuario propietario del carrito
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCarrito> items; // Lista de items en el carrito
}