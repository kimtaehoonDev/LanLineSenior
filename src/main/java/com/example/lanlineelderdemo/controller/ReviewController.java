package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.controller.dto.CreateReviewRequestDto;
import com.example.lanlineelderdemo.service.review.CreateReviewRequestServiceDto;
import com.example.lanlineelderdemo.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public String postReview(@RequestParam Long restaurantId,
                             @Validated @ModelAttribute CreateReviewRequestDto createReviewRequestDto) {
        reviewService.leaveReview(new CreateReviewRequestServiceDto(restaurantId, createReviewRequestDto.getContent(),
                createReviewRequestDto.getName(), createReviewRequestDto.getPassword()));
        return "redirect:/restaurants/"+restaurantId;
    }
}
