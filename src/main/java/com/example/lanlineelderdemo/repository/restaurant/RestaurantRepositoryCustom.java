package com.example.lanlineelderdemo.repository.restaurant;

import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.repository.restaurant.FindRestaurantBySearchConditionResponseDto;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<FindRestaurantBySearchConditionResponseDto> findRestaurantBySearchCondition(SearchCondition searchCondition);
}
