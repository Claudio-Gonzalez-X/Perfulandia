package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.assembler.PedidoModelAssembler;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.service.PedidoService;
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
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final PedidoModelAssembler assembler;

    public PedidoController(PedidoService service, PedidoModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Pedido>> listar() {
        List<EntityModel<Pedido>> pedidos = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Pedido pedido) {
        Pedido savedPedido = service.guardar(pedido);
        EntityModel<Pedido> entityModel = assembler.toModel(savedPedido);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Pedido> buscar(@PathVariable long id) {
        // TODO: Consider adding orElseThrow for not found
        Pedido pedido = service.buscarPorId(id);
        return assembler.toModel(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        service.eliminar(id); // TODO: Consider if service.eliminar should throw an exception if not found
        return ResponseEntity.noContent().build();
    }
}
