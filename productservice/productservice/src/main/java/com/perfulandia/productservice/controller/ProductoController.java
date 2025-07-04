package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.assembler.ProductoModelAssembler;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.Usuario; // This remains for the obtenerUsuario method
import com.perfulandia.productservice.service.ProductoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // This remains for the obtenerUsuario method

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService servicio;
    private final ProductoModelAssembler assembler;
    private final RestTemplate restTemplate; // This remains for the obtenerUsuario method

    public ProductoController(ProductoService servicio, ProductoModelAssembler assembler, RestTemplate restTemplate){
        this.servicio = servicio;
        this.assembler = assembler;
        this.restTemplate = restTemplate;
    }

    //listar
    @GetMapping
    public CollectionModel<EntityModel<Producto>> listar(){
        List<EntityModel<Producto>> productos = servicio.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    //guardar
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto){
        Producto savedProducto = servicio.guardar(producto);
        EntityModel<Producto> entityModel = assembler.toModel(savedProducto);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //buscar x id
    @GetMapping("/{id}")
    public EntityModel<Producto> buscar(@PathVariable long id){
        // TODO: Consider adding orElseThrow for not found
        Producto producto = servicio.bucarPorId(id);
        return assembler.toModel(producto);
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        servicio.eliminar(id); // TODO: Consider if service.eliminar should throw an exception if not found
        return ResponseEntity.noContent().build();
    }

    //Nuevo método - remains as is, does not return HATEOAS model for Usuario from this service
    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable long id){
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/"+id,Usuario.class);
    }
}
