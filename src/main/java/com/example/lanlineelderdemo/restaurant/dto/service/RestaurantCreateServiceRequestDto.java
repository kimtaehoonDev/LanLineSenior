package com.example.lanlineelderdemo.restaurant.dto.service;

import com.example.lanlineelderdemo.restaurant.domain.FoodCategory;
import com.example.lanlineelderdemo.restaurant.domain.Location;
import com.example.lanlineelderdemo.restaurant.domain.Restaurant;
import com.sun.istack.NotNull;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.lang.Nullable;

@Data
public class RestaurantCreateServiceRequestDto {
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

    public RestaurantCreateServiceRequestDto(Row row) {
        this.name = row.getCell(0).getStringCellValue();
        this.geoLocationX = row.getCell(1).getNumericCellValue();
        this.geoLocationY = row.getCell(2).getNumericCellValue();
        this.location = Location.find(row.getCell(3).getStringCellValue());
        this.category = FoodCategory.find(row.getCell(4).getStringCellValue());
        this.isAtmosphere = row.getCell(5).getBooleanCellValue();
        this.hasCostPerformance = row.getCell(6).getBooleanCellValue();
        this.canEatSingle = row.getCell(7).getBooleanCellValue();
        if (row.getCell(8) != null) {
            this.adminComment = row.getCell(8).getStringCellValue();
        }
        this.url = row.getCell(9).getStringCellValue();
    }

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
