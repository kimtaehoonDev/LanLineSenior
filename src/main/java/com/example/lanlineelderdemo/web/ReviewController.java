package com.example.lanlineelderdemo.web;

import com.example.lanlineelderdemo.review.ReviewService;
import com.example.lanlineelderdemo.review.dto.ReviewCreateRequestDto;
import com.example.lanlineelderdemo.review.dto.ReviewCreateServiceRequestDto;
import com.example.lanlineelderdemo.review.dto.ReviewDeleteRequestDto;
import com.example.lanlineelderdemo.review.dto.ReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public String postReview(@RequestParam Long restaurantId,
                             @Validated @ModelAttribute ReviewCreateRequestDto reviewCreateRequestDto) {
        reviewService.leaveReview(new ReviewCreateServiceRequestDto(restaurantId, reviewCreateRequestDto.getContent(),
                reviewCreateRequestDto.getName(), reviewCreateRequestDto.getPassword()));
        return "redirect:/restaurants/"+restaurantId;
    }

    //     TODO 수정폼 만들기 -> 작은창으로 뜨세.
    @GetMapping("/edit")
    public String editForm(@ModelAttribute ReviewUpdateRequestDto reviewUpdateRequestDto) {
        return "/review/editForm";
    }

    @PatchMapping
    public String updateReview(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                               @Validated @ModelAttribute ReviewUpdateRequestDto reviewUpdateRequestDto) {
        reviewService.updateReview(reviewId, reviewUpdateRequestDto.changeServiceDto());
        return "redirect:/restaurants/"+restaurantId;
    }

    // 삭제 비밀번호 받는 폼 만들기.
    @GetMapping("/delete")
    public String deleteForm(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                             @ModelAttribute ReviewDeleteRequestDto reviewDeleteRequestDto) {
        return "/review/deleteForm";
    }

    @DeleteMapping
    public String deleteReview(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                               @Validated @ModelAttribute ReviewDeleteRequestDto reviewDeleteRequestDto) {
        reviewService.deleteReview(reviewId, reviewDeleteRequestDto.getPassword());
        return "redirect:/restaurants/"+restaurantId;
    }
}
