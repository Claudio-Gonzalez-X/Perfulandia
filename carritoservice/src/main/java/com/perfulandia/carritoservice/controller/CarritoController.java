package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.assembler.CarritoModelAssembler;
import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.service.CarritoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    private final CarritoService service;
    private final CarritoModelAssembler assembler;

    //constructor
    public CarritoController(CarritoService service, CarritoModelAssembler assembler){
        this.service = service;
        this.assembler = assembler;
    }

    //listar
    @GetMapping
    public CollectionModel<EntityModel<Carrito>> listar(){
        List<EntityModel<Carrito>> carritos = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(carritos,
                linkTo(methodOn(CarritoController.class).listar()).withSelfRel());
    }

    //guardar
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Carrito carrito){
        Carrito savedCarrito = service.guardar(carrito);
        EntityModel<Carrito> entityModel = assembler.toModel(savedCarrito);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //buscar por id
    @GetMapping("/{id}")
    public EntityModel<Carrito> buscar(@PathVariable long id){
        // TODO: Consider adding orElseThrow for not found, or checking if service.buscar already handles it
        // For example: Carrito carrito = service.buscar(id).orElseThrow(() -> new RuntimeNotFoundException(id));
        Carrito carrito = service.buscar(id);
        return assembler.toModel(carrito);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        service.eliminar(id); // TODO: Consider if service.eliminar should throw an exception if not found
        return ResponseEntity.noContent().build();
    }
}
