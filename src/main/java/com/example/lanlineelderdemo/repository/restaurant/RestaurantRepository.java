package com.example.lanlineelderdemo.repository.restaurant;

import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
    public Optional<Restaurant> findByName(String name);
}
