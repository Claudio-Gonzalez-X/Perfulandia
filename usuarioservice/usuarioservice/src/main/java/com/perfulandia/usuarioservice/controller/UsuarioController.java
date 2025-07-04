package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.assembler.UsuarioModelAssembler;
import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioService service, UsuarioModelAssembler assembler){
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Usuario>> listar(){
        List<EntityModel<Usuario>> usuarios = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        Usuario savedUsuario = service.guardar(usuario);
        EntityModel<Usuario> entityModel = assembler.toModel(savedUsuario);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Usuario> buscar(@PathVariable long id){
        // TODO: Consider adding orElseThrow for not found
        Usuario usuario = service.buscar(id);
        return assembler.toModel(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        service.eliminar(id); // TODO: Consider if service.eliminar should throw an exception if not found
        return ResponseEntity.noContent().build();
    }
}
