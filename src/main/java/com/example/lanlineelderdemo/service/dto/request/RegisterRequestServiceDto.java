package com.example.lanlineelderdemo.service.dto.request;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.GeoLocation;
import com.example.lanlineelderdemo.domain.Location;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class RegisterRequestServiceDto {
    private String name;

    private Double geoLocationX;

    private Double geoLocationY;

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;

    private String telNum;
}
