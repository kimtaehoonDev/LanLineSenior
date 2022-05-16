package com.example.lanlineelderdemo.web;

import com.example.lanlineelderdemo.review.ReviewService;
import com.example.lanlineelderdemo.review.dto.ReviewResponseDto;
import com.example.lanlineelderdemo.web.form.review.ReviewCreateForm;
import com.example.lanlineelderdemo.review.dto.ReviewCreateServiceRequestDto;
import com.example.lanlineelderdemo.web.form.review.ReviewDeleteForm;
import com.example.lanlineelderdemo.web.form.review.ReviewUpdateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public String postReview(@RequestParam Long restaurantId,
                             @Validated @ModelAttribute ReviewCreateForm reviewCreateForm) {
        reviewService.leaveReview(new ReviewCreateServiceRequestDto(restaurantId, reviewCreateForm.getContent(),
                reviewCreateForm.getName(), reviewCreateForm.getPassword()));
        return "redirect:/restaurants/"+restaurantId;
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                           @ModelAttribute ReviewUpdateForm reviewUpdateForm, Model model) {
        model.addAttribute("review", reviewService.findReview(reviewId));
        return "/review/editForm";
    }

    @PatchMapping
    public String updateReview(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                               @Validated @ModelAttribute ReviewUpdateForm reviewUpdateForm) {
        reviewService.updateReview(reviewId, reviewUpdateForm.changeServiceDto());
        return "redirect:/restaurants/"+restaurantId;
    }

    @GetMapping("/delete")
    public String deleteForm(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                             @ModelAttribute ReviewDeleteForm reviewDeleteForm) {
        return "/review/deleteForm";
    }

    @DeleteMapping
    public String deleteReview(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                               @Validated @ModelAttribute ReviewDeleteForm reviewDeleteForm) {
        reviewService.deleteReview(reviewId, reviewDeleteForm.getPassword());
        return "redirect:/restaurants/"+restaurantId;
    }
}
