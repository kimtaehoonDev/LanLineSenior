package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Restaurant;
import com.example.lanlineelderdemo.domain.SearchCondition;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<FindRestaurantBySearchConditionResponseDto> findRestaurantBySearchCondition(SearchCondition searchCondition);
}
