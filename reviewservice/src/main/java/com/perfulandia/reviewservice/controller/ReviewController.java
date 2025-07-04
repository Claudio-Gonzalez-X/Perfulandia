package com.perfulandia.reviewservice.controller;

import com.perfulandia.reviewservice.assembler.ReviewModelAssembler;
import com.perfulandia.reviewservice.model.Review;
import com.perfulandia.reviewservice.service.ReviewService;
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
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;
    private final ReviewModelAssembler assembler;

    public ReviewController(ReviewService service, ReviewModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Review>> listar() {
        List<EntityModel<Review>> reviews = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(reviews,
                linkTo(methodOn(ReviewController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Review review) {
        Review savedReview = service.guardar(review);
        EntityModel<Review> entityModel = assembler.toModel(savedReview);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Review> buscar(@PathVariable long id) {
        // TODO: Consider adding orElseThrow for not found
        Review review = service.buscarPorId(id);
        return assembler.toModel(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody Review review) {
        Review updatedReview = service.actualizar(id, review); // Assuming service returns the updated review
        EntityModel<Review> entityModel = assembler.toModel(updatedReview);
        return ResponseEntity
                .ok(entityModel); // Or .created if appropriate, though PUT usually means update
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        service.eliminar(id); // TODO: Consider if service.eliminar should throw an exception if not found
        return ResponseEntity.noContent().build();
    }
}
