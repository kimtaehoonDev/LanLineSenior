package com.example.lanlineelderdemo.service.dto.response;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.Restaurant;
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

    public static ShowRestaurantDetailsResponseDto of(Restaurant restaurant) {
        ShowRestaurantDetailsResponseDto showRestaurantDetailsResponseDto = new ShowRestaurantDetailsResponseDto();
        showRestaurantDetailsResponseDto.id = restaurant.getId();
        showRestaurantDetailsResponseDto.name = restaurant.getName();
        showRestaurantDetailsResponseDto.location = restaurant.getLocation();
        showRestaurantDetailsResponseDto.category = restaurant.getCategory();
        showRestaurantDetailsResponseDto.isAtmosphere = restaurant.getIsAtmosphere();
        showRestaurantDetailsResponseDto.hasCostPerformance = restaurant.getHasCostPerformance();
        showRestaurantDetailsResponseDto.canEatSingle = restaurant.getCanEatSingle();
        showRestaurantDetailsResponseDto.adminComment = restaurant.getAdminComment();
        showRestaurantDetailsResponseDto.locationX = restaurant.getGeoLocation().getLocationX();
        showRestaurantDetailsResponseDto.locationY = restaurant.getGeoLocation().getLocationY();
        showRestaurantDetailsResponseDto.url = restaurant.getUrl();

        return showRestaurantDetailsResponseDto;
    }
}
