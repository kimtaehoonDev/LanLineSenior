package com.example.lanlineelderdemo.review.repository;

import com.example.lanlineelderdemo.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

}
