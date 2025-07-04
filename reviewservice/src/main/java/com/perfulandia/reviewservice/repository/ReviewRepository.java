package com.perfulandia.reviewservice.repository;

import com.perfulandia.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
