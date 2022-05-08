package com.example.lanlineelderdemo.review.repository;

import com.example.lanlineelderdemo.review.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.lanlineelderdemo.review.QReview.review;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public ReviewRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Review> findReviewsByRestaurantId(Long restaurantId) {
        return queryFactory.selectFrom(review).where(review.restaurant.id.eq(restaurantId),
                        review.isUsing.eq(Boolean.TRUE))
                .orderBy(review.id.asc())
                .fetch();
    }
}
