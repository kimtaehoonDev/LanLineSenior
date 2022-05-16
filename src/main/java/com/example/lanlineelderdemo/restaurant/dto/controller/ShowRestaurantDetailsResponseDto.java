package com.example.lanlineelderdemo.restaurant.dto.controller;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantRecommendMenuDto;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantResponseDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShowRestaurantDetailsResponseDto {
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

    private String lunchMenuName;
    private Integer lunchMenuPrice;

    private String dinnerMenuName;
    private Integer dinnerMenuPrice;


    public static ShowRestaurantDetailsResponseDto create(RestaurantResponseDto restaurant, RestaurantRecommendMenuDto recommendMenu) {
        ShowRestaurantDetailsResponseDto showRestaurantDetailsResponseDto = new ShowRestaurantDetailsResponseDto();
        showRestaurantDetailsResponseDto.id = restaurant.getId();
        showRestaurantDetailsResponseDto.name = restaurant.getName();
        showRestaurantDetailsResponseDto.location = restaurant.getLocation();
        showRestaurantDetailsResponseDto.category = restaurant.getCategory();
        showRestaurantDetailsResponseDto.isAtmosphere = restaurant.getIsAtmosphere();
        showRestaurantDetailsResponseDto.hasCostPerformance = restaurant.getHasCostPerformance();
        showRestaurantDetailsResponseDto.canEatSingle = restaurant.getCanEatSingle();
        showRestaurantDetailsResponseDto.adminComment = restaurant.getAdminComment();
        showRestaurantDetailsResponseDto.locationX = restaurant.getLocationX();
        showRestaurantDetailsResponseDto.locationY = restaurant.getLocationY();
        showRestaurantDetailsResponseDto.url = restaurant.getUrl();
        showRestaurantDetailsResponseDto.lunchMenuName = recommendMenu.getLunchMenuName();
        showRestaurantDetailsResponseDto.lunchMenuPrice = recommendMenu.getLunchMenuPrice();
        showRestaurantDetailsResponseDto.dinnerMenuName = recommendMenu.getDinnerMenuName();
        showRestaurantDetailsResponseDto.dinnerMenuPrice = recommendMenu.getDinnerMenuPrice();
        return showRestaurantDetailsResponseDto;
    }
}
