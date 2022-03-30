package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom{
    public Optional<Restaurant> findByName(String name);
}
