package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
}
