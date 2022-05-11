package com.example.lanlineelderdemo.restaurant.repository.dto;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.GeoLocation;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindRestaurantBySearchConditionResponseDto {
    private Long id;
    private String name;
    private Location location;
    private GeoLocation geoLocation;
    private FoodCategory category;
    private Boolean isAtmosphere;
    private Boolean hasCostPerformance;
    private Boolean canEatSingle;
    private String adminComment; //null 가능.
    private String url;
    private OpenType openType;
    private String menuName;
    private Integer numberOfMeal;
    private Integer price;

}
