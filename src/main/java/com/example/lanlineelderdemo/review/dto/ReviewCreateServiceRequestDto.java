package com.example.lanlineelderdemo.review.dto;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewCreateServiceRequestDto {

    private Long restaurantId;

    @NotBlank
    private String content;

    @NotBlank
    private String writerName;

    @NotBlank
    private String password;

    public ReviewCreateServiceRequestDto(Long restaurantId, String content, String name, String password) {
        this.restaurantId = restaurantId;
        this.content = content;
        this.writerName = name;
        this.password = password;
    }

    public Review toEntity(Restaurant restaurant) {
        return Review.createReview()
                .restaurant(restaurant)
                .content(content)
                .writerName(writerName)
                .password(password)
                .build();
    }
}
