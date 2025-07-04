package com.perfulandia.reviewservice.service;

import com.perfulandia.reviewservice.model.Review;
import com.perfulandia.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> listar() {
        return reviewRepository.findAll();
    }

    public Review guardar(Review review) {
        return reviewRepository.save(review);
    }

    public Review buscarPorId(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public void eliminar(long id) {
        reviewRepository.deleteById(id);
    }

    public Review actualizar(long id, Review review) {
        if (reviewRepository.existsById(id)) {
            review.setId(id); // Asegúrate de que el ID se mantenga
            return reviewRepository.save(review);
        }
        return null; // O lanza una excepción si no se encuentra
    }
}
