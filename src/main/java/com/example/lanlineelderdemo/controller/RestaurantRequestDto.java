package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.GeoLocation;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.service.dto.request.RegisterRequestServiceDto;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

public class RestaurantRequestDto {

    @NotNull
    private String name;

    @NotNull
    private Double geoLocationX;

    @NotNull
    private Double geoLocationY;

    @NotNull
    private Location location; //Enum을 바로 받을 수 있나?

    @NotNull
    private FoodCategory category;

    @NotNull
    private Boolean isAtmosphere;

    @NotNull
    private Boolean hasCostPerformance;

    @NotNull
    private Boolean canEatSingle;

    @Nullable
    private String adminComment;

    @NotNull
    private Integer minCost;

    @Nullable
    private String telNum;

    public RegisterRequestServiceDto changeRequestServiceDto() {
        RegisterRequestServiceDto registerRequestServiceDto = new RegisterRequestServiceDto();
        registerRequestServiceDto.setName(name);
        registerRequestServiceDto.setGeoLocationX(geoLocationX);
        registerRequestServiceDto.setGeoLocationY(geoLocationY);
        registerRequestServiceDto.setLocation(location);
        registerRequestServiceDto.setCategory(category);
        registerRequestServiceDto.setIsAtmosphere(isAtmosphere);
        registerRequestServiceDto.setHasCostPerformance(hasCostPerformance);
        registerRequestServiceDto.setCanEatSingle(canEatSingle);
        registerRequestServiceDto.setAdminComment(adminComment);
        registerRequestServiceDto.setMinCost(minCost);
        registerRequestServiceDto.setTelNum(telNum);
        return registerRequestServiceDto;
    }
}
