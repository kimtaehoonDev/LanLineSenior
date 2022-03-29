package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> findRestaurantBySearchDto(SearchRequestRepositoryDto searchRequestRepositoryDto);
}
