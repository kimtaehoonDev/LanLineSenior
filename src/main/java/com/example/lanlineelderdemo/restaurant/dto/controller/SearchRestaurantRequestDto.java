package com.example.lanlineelderdemo.restaurant.dto.controller;

import com.example.lanlineelderdemo.restaurant.domain.FoodCategory;
import com.example.lanlineelderdemo.restaurant.domain.Location;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.SearchCondition;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchRestaurantRequestDto {

    private List<Location> locations;

    private List<FoodCategory> unselectedCategories;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private Integer maxCostLine;

    private OpenType openType;

    public SearchCondition toEntity() {
        return new SearchCondition(locations, unselectedCategories, isAtmosphere,
                hasCostPerformance, canEatSingle, maxCostLine, openType);


    }
}
