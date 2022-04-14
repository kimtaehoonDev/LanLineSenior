package com.example.lanlineelderdemo.service.dto.request;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.GeoLocation;
import com.example.lanlineelderdemo.domain.Location;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Embedded;

@Data
public class RegisterRequestServiceDto {
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

    @NotNull
    private Integer minCost;

    @Nullable
    private String telNum;

    private String address;

    private String url;

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
//        registerRequestServiceDto.setAddress(address);
//        registerRequestServiceDto.setUrl(url);
        return registerRequestServiceDto;
    }
}
