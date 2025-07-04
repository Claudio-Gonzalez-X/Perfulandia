package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.service.CarritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")

public class CarritoController {

    private final CarritoService service;

    //constructor
    public CarritoController(CarritoService service){
        this.service=service;
    }

    //listar
    @GetMapping
    public List<Carrito> listar(){
        return service.listar();
    }

    //guardar
    @PostMapping
    public Carrito guardar(@RequestBody Carrito carrito){
        return service.guardar(carrito);
    }

    //buscar por id
    @GetMapping("/{id}")
    public Carrito buscar(@PathVariable long id){
        return service.buscar(id);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id){
        service.eliminar(id);
    }

}
