package com.example.lanlineelderdemo.service.dto;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import lombok.Data;

@Data
public class RegisterRequestServiceDto {
    private String name;

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;

    private Integer maxCost;
}
