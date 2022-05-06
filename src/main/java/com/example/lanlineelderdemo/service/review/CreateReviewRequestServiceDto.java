package com.example.lanlineelderdemo.service.review;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.Data;

@Data
public class CreateReviewRequestServiceDto {

    private Long restaurantId;

    private String content;

    private String writerName;

    private String password;

    public Review toEntity(Restaurant restaurant) {
        return Review.createReview()
                .restaurant(restaurant)
                .content(content)
                .writerName(writerName)
                .password(password)
                .build();
    }
}
