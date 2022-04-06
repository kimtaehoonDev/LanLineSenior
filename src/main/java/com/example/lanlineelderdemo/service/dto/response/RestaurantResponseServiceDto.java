package com.example.lanlineelderdemo.service.dto.response;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import lombok.Data;

@Data
public class RestaurantResponseServiceDto {
    private Long id;

    private String name;

    //좌표 정보

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;
}
