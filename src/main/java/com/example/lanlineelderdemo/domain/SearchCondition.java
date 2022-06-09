package com.example.lanlineelderdemo.domain;

import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchCondition {

    private List<Location> locations;

    private List<FoodCategory> unselectedCategories;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private Integer maxCostLine;

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
