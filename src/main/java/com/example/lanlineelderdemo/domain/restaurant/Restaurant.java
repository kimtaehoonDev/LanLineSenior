package com.example.lanlineelderdemo.domain.restaurant;

import com.example.lanlineelderdemo.utils.Encoder;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name","location","category"})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Location location;

    @NotNull
    @Embedded
    private GeoLocation geoLocation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodCategory category;

    @NotNull
    private Boolean isAtmosphere;

    @NotNull
    private Boolean hasCostPerformance;

    @NotNull
    private Boolean canEatSingle;

    @Nullable
    private String adminComment;

    @Nullable
    private String url;

    @Builder(builderClassName = "createRestaurant", builderMethodName = "createRestaurant")
    private Restaurant(String name, Location location, Double geoLocationX, Double geoLocationY,
                       FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance,
                       Boolean canEatSingle, String adminComment, String url) {
        validate(name, location, category, geoLocationX, geoLocationY, isAtmosphere, hasCostPerformance,
                canEatSingle, url);
        this.name = name;
        this.location = location;
        this.geoLocation = new GeoLocation(geoLocationX, geoLocationY);
        this.category = category;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.adminComment = adminComment;
        this.url = url;
    }

    private void validate(String name, Location location, FoodCategory category, Double geoLocationX,
                          Double geoLocationY, Boolean isAtmosphere, Boolean hasCostPerformance,
                          Boolean canEatSingle, String url) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 누락되었습니다.");
        }
        validate(location, geoLocationX, geoLocationY, category, isAtmosphere, hasCostPerformance,
                canEatSingle, url);
    }

    // TODO UPDATE


    private void validate(Location location, Double geoLocationX, Double geoLocationY, FoodCategory category,
                          Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle,
                          String url) {
        if (location == null) {
            throw new IllegalArgumentException("위치 정보가 누락되었습니다.");
        }
        if (geoLocationX == null || geoLocationY == null){
            throw new IllegalArgumentException("좌표 정보가 누락되었습니다.");
        }
        if (category == null) {
            throw new IllegalArgumentException("카테고리 정보가 누락되었습니다.");
        }
        if (isAtmosphere == null) {
            throw new IllegalArgumentException("분위기 관련 정보가 누락되었습니다.");
        }
        if (hasCostPerformance == null) {
            throw new IllegalArgumentException("가성비 관련 정보가 누락되었습니다.");
        }
        if (canEatSingle == null) {
            throw new IllegalArgumentException("혼밥 가능 관련 정보가 누락되었습니다.");
        }
    }
}
