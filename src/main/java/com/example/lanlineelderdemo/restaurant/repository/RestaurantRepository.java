package com.example.lanlineelderdemo.restaurant.repository;

import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
    Optional<Restaurant> findByName(String name);
}
