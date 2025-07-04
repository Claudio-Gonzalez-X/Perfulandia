package com.perfulandia.reviewservice.controller;

import com.perfulandia.reviewservice.model.Review;
import com.perfulandia.reviewservice.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> listar() {
        return service.listar();
    }

    @PostMapping
    public Review guardar(@RequestBody Review review) {
        return service.guardar(review);
    }

    @GetMapping("/{id}")
    public Review buscar(@PathVariable long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }
    @PutMapping("/{id}")
    public Review actualizar(@PathVariable long id, @RequestBody Review review) {
        return service.actualizar(id, review);
    }

}
