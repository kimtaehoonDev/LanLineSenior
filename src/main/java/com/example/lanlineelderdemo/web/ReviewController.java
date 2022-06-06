package com.example.lanlineelderdemo.web;

import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.restaurant.dto.service.SearchRestaurantResponseDto;
import com.example.lanlineelderdemo.review.ReviewService;
import com.example.lanlineelderdemo.review.dto.ReviewResponseDto;
import com.example.lanlineelderdemo.review.dto.ReviewUpdateServiceRequestDto;
import com.example.lanlineelderdemo.web.form.restaurant.SearchForm;
import com.example.lanlineelderdemo.web.form.review.ReviewCreateForm;
import com.example.lanlineelderdemo.review.dto.ReviewCreateServiceRequestDto;
import com.example.lanlineelderdemo.web.form.review.ReviewDeleteForm;
import com.example.lanlineelderdemo.web.form.review.ReviewUpdateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private static final Long PASSWORD_NOT_CORRECT_ERROR = -1L;

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
        model.addAttribute("reviewUpdateForm", reviewUpdateForm);
        return "/review/editForm";
    }
    
    @ResponseBody
    @PatchMapping
    public ResponseEntity<Long> updateReview(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                                             @ModelAttribute ReviewUpdateServiceRequestDto reviewUpdateServiceRequestDto) {
        try {
            return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewUpdateServiceRequestDto),HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(PASSWORD_NOT_CORRECT_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete")
    public String deleteForm(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                             @ModelAttribute ReviewDeleteForm reviewDeleteForm, Model model) {
        model.addAttribute("reviewDeleteForm", reviewDeleteForm);
        return "/review/deleteForm";
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity<Long> deleteReview(@RequestParam Long restaurantId, @RequestParam Long reviewId,
                             @ModelAttribute ReviewDeleteForm reviewDeleteForm) {
        try {
            reviewService.deleteReview(reviewId, reviewDeleteForm.getPassword());
            return new ResponseEntity<>(reviewId,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(PASSWORD_NOT_CORRECT_ERROR, HttpStatus.BAD_REQUEST);
        }
    }
}
