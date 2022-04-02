package com.example.lanlineelderdemo.domain;

import lombok.Data;

import java.util.List;

@Data
public class SearchCondition {

    private List<Location> locations;

    private List<FoodCategory> unselectedCategories;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private Integer maxCostLine; //예산 최대한도
}
