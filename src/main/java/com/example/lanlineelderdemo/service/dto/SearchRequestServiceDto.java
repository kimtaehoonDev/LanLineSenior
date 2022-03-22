package com.example.lanlineelderdemo.service.dto;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import lombok.Data;

import java.util.List;

@Data
public class SearchRequestServiceDto {
    private Location location;

    private List<FoodCategory> unselectedCategories;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private Integer minCost;

    private Integer maxCost;
}
