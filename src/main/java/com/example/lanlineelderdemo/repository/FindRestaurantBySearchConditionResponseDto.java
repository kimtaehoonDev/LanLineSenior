package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.GeoLocation;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.OpenType;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

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

    public FindRestaurantBySearchConditionResponseDto(Long id, String name, Location location, GeoLocation geoLocation,
                                                      FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance,
                                                      Boolean canEatSingle, String adminComment, String url, OpenType openType,
                                                      String menuName, Integer numberOfMeal, int price) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.geoLocation = geoLocation;
        this.category = category;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.adminComment = adminComment;
        this.url = url;
        this.openType = openType;
        this.menuName = menuName;
        this.numberOfMeal = numberOfMeal;
        this.price = price;
    }
}
