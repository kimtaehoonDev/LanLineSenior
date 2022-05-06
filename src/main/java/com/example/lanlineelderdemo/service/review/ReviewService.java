package com.example.lanlineelderdemo.service.review;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.example.lanlineelderdemo.repository.ReviewRepository;
import com.example.lanlineelderdemo.repository.restaurant.RestaurantRepository;
import com.example.lanlineelderdemo.service.restaurant.response.InqueryRestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
     */
    public InqueryRestaurantReviewsResponseDto inqueryRestaurantReviews(Long restaurantId) {
        List<Review> reviews = reviewRepository.findReviewsByRestaurantId(restaurantId);
        return InqueryRestaurantReviewsResponseDto.of(reviews); //1급 콜렉션
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
