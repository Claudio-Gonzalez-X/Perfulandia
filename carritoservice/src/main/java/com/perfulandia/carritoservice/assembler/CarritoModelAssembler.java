package com.perfulandia.carritoservice.assembler;

import com.perfulandia.carritoservice.controller.CarritoController;
import com.perfulandia.carritoservice.model.Carrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoController.class).buscar(carrito.getId())).withSelfRel(),
                linkTo(methodOn(CarritoController.class).listar()).withRel("carritos"));
        // We could also add links to items if there was a controller method for them:
        // linkTo(methodOn(CarritoController.class).listarItems(carrito.getId())).withRel("items"));
    }
}
