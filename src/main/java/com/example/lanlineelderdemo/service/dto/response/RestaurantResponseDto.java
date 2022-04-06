package com.example.lanlineelderdemo.service.dto.response;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Restaurant;
import lombok.Data;

@Data
public class RestaurantResponseDto {
    private Long id;

    private String name;

    //좌표 정보

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;

    public static RestaurantResponseDto makeUsingRestaurant(Restaurant restaurant) {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();
        restaurantResponseDto.id = restaurant.getId();
        restaurantResponseDto.name = restaurant.getName();
        restaurantResponseDto.category = restaurant.getCategory();
        restaurantResponseDto.isAtmosphere = restaurant.getIsAtmosphere();
        restaurantResponseDto.hasCostPerformance = restaurant.getHasCostPerformance();
        restaurantResponseDto.canEatSingle = restaurant.getCanEatSingle();
        restaurantResponseDto.adminComment = restaurant.getAdminComment();
        restaurantResponseDto.minCost = restaurant.getMinCost();
        return restaurantResponseDto;


    }
}
