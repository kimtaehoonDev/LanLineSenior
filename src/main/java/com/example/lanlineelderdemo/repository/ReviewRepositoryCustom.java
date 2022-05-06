package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.repository.restaurant.FindRestaurantBySearchConditionResponseDto;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findReviewsByRestaurantId(Long restaurantId);

}
