package com.perfulandia.reviewservice.assembler;

import com.perfulandia.reviewservice.controller.ReviewController;
import com.perfulandia.reviewservice.model.Review;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @Override
    public EntityModel<Review> toModel(Review review) {
        EntityModel<Review> reviewModel = EntityModel.of(review,
                linkTo(methodOn(ReviewController.class).buscar(review.getId())).withSelfRel(),
                linkTo(methodOn(ReviewController.class).listar()).withRel("reviews"));

        // Link to update method
        reviewModel.add(linkTo(methodOn(ReviewController.class).actualizar(review.getId(), review)).withRel("update"));
        // Link to delete method - typically not part of the model for GET, but can be added
        // reviewModel.add(linkTo(methodOn(ReviewController.class).eliminar(review.getId())).withRel("delete"));

        return reviewModel;
    }
}
