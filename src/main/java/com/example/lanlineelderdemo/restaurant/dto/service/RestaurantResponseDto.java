package com.example.lanlineelderdemo.restaurant.dto.service;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RestaurantResponseDto {
    private Long id;

    private String name;

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Double locationX;

    private Double locationY;

    private String url;

    public static RestaurantResponseDto of(Restaurant restaurant) {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();
        restaurantResponseDto.id = restaurant.getId();
        restaurantResponseDto.name = restaurant.getName();
        restaurantResponseDto.location = restaurant.getLocation();
        restaurantResponseDto.category = restaurant.getCategory();
        restaurantResponseDto.isAtmosphere = restaurant.getIsAtmosphere();
        restaurantResponseDto.hasCostPerformance = restaurant.getHasCostPerformance();
        restaurantResponseDto.canEatSingle = restaurant.getCanEatSingle();
        restaurantResponseDto.adminComment = restaurant.getAdminComment();
        restaurantResponseDto.locationX = restaurant.getGeoLocation().getLocationX();
        restaurantResponseDto.locationY = restaurant.getGeoLocation().getLocationY();
        restaurantResponseDto.url = restaurant.getUrl();

        return restaurantResponseDto;
    }
}
