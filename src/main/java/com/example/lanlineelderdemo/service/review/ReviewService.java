package com.example.lanlineelderdemo.service.review;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.example.lanlineelderdemo.repository.ReviewRepository;
import com.example.lanlineelderdemo.repository.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Create
     */
    @Transactional
    public Long leaveReview(CreateReviewRequestServiceDto createReviewRequestServiceDto) {
        Restaurant restaurant = getRestaurant(createReviewRequestServiceDto.getRestaurantId());
        Review review = createReviewRequestServiceDto.toEntity(restaurant);
        reviewRepository.save(review);
        return review.getId();
    }

    private Restaurant getRestaurant(Long restaurantId) {
        Optional<Restaurant> parsingRestaurant = restaurantRepository.findById(restaurantId);
        if (parsingRestaurant.isEmpty()) {
            throw new IllegalArgumentException("Restaurant가 존재하지 않습니다.");
        }
        return parsingRestaurant.get();
    }

    /**
     * Read
     * @return
     */
    public List<ReviewResponseDto> inqueryRestaurantReviews(Long restaurantId) {
        List<Review> reviews = reviewRepository.findReviewsByRestaurantId(restaurantId);
        return reviews.stream().map(review -> ReviewResponseDto.from(review)).collect(Collectors.toList());
    }

    /**
     * Update
     */
    @Transactional
    public Long updateReview(Long reviewId, UpdateReviewRequestServiceDto updateReviewRequestServiceDto) {
        Review review = getReview(reviewId);
        review.update(updateReviewRequestServiceDto.getWriterName(),
                updateReviewRequestServiceDto.getContent(), updateReviewRequestServiceDto.getPassword());
        return review.getRestaurant().getId();
    }

    /**
     * Delete
     */
    @Transactional
    public Long deleteReview(Long reviewId, String password) {
        Review review = getReview(reviewId);
        review.delete(password);
        return review.getRestaurant().getId();
    }

    private Review getReview(Long reviewId) {
        Optional<Review> parsingReview = reviewRepository.findById(reviewId);
        if (parsingReview.isEmpty()) {
            throw new IllegalArgumentException("해당 review는 존재하지 않습니다.");
        }
        return parsingReview.get();
    }

//    public List<CommentResponseDto>
}