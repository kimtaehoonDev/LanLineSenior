package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
