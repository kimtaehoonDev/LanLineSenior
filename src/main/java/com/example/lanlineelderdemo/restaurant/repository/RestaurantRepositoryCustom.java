package com.example.lanlineelderdemo.restaurant.repository;

import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.restaurant.repository.dto.FindRestaurantBySearchConditionResponseDto;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<FindRestaurantBySearchConditionResponseDto> findRestaurantBySearchCondition(SearchCondition searchCondition);
}
