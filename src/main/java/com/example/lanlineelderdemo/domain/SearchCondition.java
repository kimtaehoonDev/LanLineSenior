package com.example.lanlineelderdemo.domain;

import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
// 모든 조건이 null이어도 됨.
// 다만, Boolean조건 3개는 false여서는 안됨. true일 수는 있는데.
@ToString
public class SearchCondition {

    private List<Location> locations;

    private List<FoodCategory> unselectedCategories;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

//    @Min, @Max => 조건 달아야하나?
    private Integer maxCostLine; //예산 최대한도(1인 기준)

    private OpenType openType;

    public SearchCondition(List<Location> locations, List<FoodCategory> unselectedCategories,
                           Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle,
                           Integer maxCostLine, OpenType openType) {
        this.locations = locations;
        this.unselectedCategories = unselectedCategories;
        this.isAtmosphere = checkIsAtmosphere(isAtmosphere);
        this.hasCostPerformance = checkHasCostPerformance(hasCostPerformance);
        this.canEatSingle = checkCanEatSingle(canEatSingle);
        this.maxCostLine = maxCostLine;
        this.openType = openType;
    }

    private Boolean checkIsAtmosphere(Boolean isAtmosphere) {
        if (isAtmosphere == null || isAtmosphere == Boolean.FALSE) {
            return null;
        }
        return isAtmosphere;
    }

    private Boolean checkHasCostPerformance(Boolean hasCostPerformance) {
        if (hasCostPerformance == null || hasCostPerformance == Boolean.FALSE) {
            return null;
        }
        return hasCostPerformance;
    }

    private Boolean checkCanEatSingle(Boolean canEatSingle) {
        if (canEatSingle == null || canEatSingle == Boolean.FALSE) {
            return null;
        }
        return canEatSingle;
    }

}
