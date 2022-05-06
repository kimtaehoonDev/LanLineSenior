package com.example.lanlineelderdemo.service.review;

import com.example.lanlineelderdemo.domain.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InqueryRestaurantReviewsResponseDto {
    private final List<ReviewServiceResponseDto> reviewDtos = new ArrayList<>();

    public static InqueryRestaurantReviewsResponseDto of(List<Review> reviews) {
        InqueryRestaurantReviewsResponseDto inqueryRestaurantReviewsResponseDto = new InqueryRestaurantReviewsResponseDto();
        inqueryRestaurantReviewsResponseDto.addAll(reviews);
        return inqueryRestaurantReviewsResponseDto;
    }

    private void addAll(List<Review> reviews) {
        reviewDtos.addAll(reviews.stream().map(review -> ReviewServiceResponseDto.from(review)).collect(Collectors.toList()));
    }

}
