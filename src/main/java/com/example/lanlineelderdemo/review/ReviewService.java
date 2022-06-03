package com.example.lanlineelderdemo.review;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.example.lanlineelderdemo.review.repository.ReviewRepository;
import com.example.lanlineelderdemo.restaurant.repository.RestaurantRepository;
import com.example.lanlineelderdemo.review.dto.ReviewCreateServiceRequestDto;
import com.example.lanlineelderdemo.review.dto.ReviewResponseDto;
import com.example.lanlineelderdemo.review.dto.ReviewUpdateServiceRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    /**
     * Create
     */
    @Transactional
    public Long leaveReview(ReviewCreateServiceRequestDto reviewCreateServiceRequestDto) {
        Restaurant restaurant = getRestaurant(reviewCreateServiceRequestDto.getRestaurantId());

        reviewCreateServiceRequestDto.setPassword(passwordEncoder.encode(reviewCreateServiceRequestDto.getPassword()));
        Review review = reviewCreateServiceRequestDto.toEntity(restaurant); //레스토랑을 주입해준다. 연관관계떄문.
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
    public Long updateReview(Long reviewId, ReviewUpdateServiceRequestDto reviewUpdateServiceRequestDto) {
        Review review = getReview(reviewId);
        validatePasswordIsSame(review, reviewUpdateServiceRequestDto.getPassword());
        review.update(reviewUpdateServiceRequestDto.getWriterName(), reviewUpdateServiceRequestDto.getContent());
        return review.getRestaurant().getId();
    }

    /**
     * Delete
     * update review set is_using=FALSE where review_id=해당아이디;
     */
    @Transactional
    public Long deleteReview(Long reviewId, String password) {
        Review review = getReview(reviewId);
        validatePasswordIsSame(review, password);
        review.delete();
        return review.getRestaurant().getId();
    }

    private void validatePasswordIsSame(Review review, String password) {
        System.out.println("review = " + review.getPassword());
        System.out.println("password = " + password);
        if (!passwordEncoder.matches(password, review.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Review getReview(Long reviewId) {
        Optional<Review> parsingReview = reviewRepository.findById(reviewId);
        if (parsingReview.isEmpty()) {
            throw new IllegalArgumentException("해당 review는 존재하지 않습니다.");
        }
        Review review = parsingReview.get();
        if (!review.getIsUsing()) {
            throw new IllegalArgumentException("해당 review는 존재하지 않습니다.");
        }
        return review;
    }

    public ReviewResponseDto findReview(Long reviewId) {
        Review review = getReview(reviewId);
        return ReviewResponseDto.from(review);
    }

//    public List<CommentResponseDto>
}
