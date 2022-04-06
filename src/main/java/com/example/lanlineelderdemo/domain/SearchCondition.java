package com.example.lanlineelderdemo.domain;

import lombok.Data;

import java.util.List;

@Data
// 모든 조건이 null이어도 됨.
// 다만, Boolean조건 3개는 false여서는 안됨. true일 수는 있는데.
public class SearchCondition {

    private List<Location> locations;

    private List<FoodCategory> unselectedCategories;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

//    @Min, @Max => 조건 달아야하나?
    private Integer maxCostLine; //예산 최대한도

    public SearchCondition(List<Location> locations, List<FoodCategory> unselectedCategories, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, Integer maxCostLine) {
        validate(locations, unselectedCategories, isAtmosphere, hasCostPerformance, canEatSingle, maxCostLine);
        this.locations = locations;
        this.unselectedCategories = unselectedCategories;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.maxCostLine = maxCostLine;
    }

    private void validate(List<Location> locations, List<FoodCategory> unselectedCategories, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, Integer maxCostLine) {
        //null이거나 true는 가능. false는 불가능.
        if (isAtmosphere != null && !isAtmosphere) {
            throw new IllegalArgumentException("잘못된 접근입니다. False를 가질 수 없습니다.");
        }
        if (hasCostPerformance != null && !hasCostPerformance) {
            throw new IllegalArgumentException("잘못된 접근입니다. False를 가질 수 없습니다.");
        }
        if (canEatSingle != null && !canEatSingle) {
            throw new IllegalArgumentException("잘못된 접근입니다. False를 가질 수 없습니다.");
        }
    }
}
