package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

}
