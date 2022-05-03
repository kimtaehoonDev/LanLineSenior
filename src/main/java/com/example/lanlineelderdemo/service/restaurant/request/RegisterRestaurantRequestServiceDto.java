package com.example.lanlineelderdemo.service.restaurant.request;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class RegisterRestaurantRequestServiceDto {
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

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    @Nullable
    private String adminComment;

    //TODO URL 타입인지 확인.
    private String url;

    public Restaurant toEntity() {
        Restaurant restaurant = Restaurant.createRestaurant()
                .name(name)
                .location(location)
                .geoLocationX(geoLocationX)
                .geoLocationY(geoLocationY)
                .category(category)
                .isAtmosphere(isAtmosphere)
                .hasCostPerformance(hasCostPerformance)
                .canEatSingle(canEatSingle)
                .adminComment(adminComment)
                .url(url)
                .build();
        return restaurant;
    }

}
