package com.perfulandia.pedidoservice.assembler;

import com.perfulandia.pedidoservice.controller.PedidoController;
import com.perfulandia.pedidoservice.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).buscar(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).listar()).withRel("pedidos"));
    }
}
