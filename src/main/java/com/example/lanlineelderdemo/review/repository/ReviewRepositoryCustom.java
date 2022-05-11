package com.example.lanlineelderdemo.review.repository;

import com.example.lanlineelderdemo.domain.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findReviewsByRestaurantId(Long restaurantId);

}
