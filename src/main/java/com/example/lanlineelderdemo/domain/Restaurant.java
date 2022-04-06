package com.example.lanlineelderdemo.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name","location","category"})
public class Restaurant {
    // TODO 공통 그거로 createDate, updateDate 등록해주기.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Location location;//위치 카테고리

    @Embedded
    private GeoLocation geoLocation; //x값, y값. 지도에 매핑하기 위함.

    @Enumerated(EnumType.STRING)
    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    @Nullable
    private String adminComment; //null 가능.

    private Integer minCost;

    @Nullable
    private String telNum; // TODO 이후에 matches 사용해주기.

    // TODO 영업시간 관련 정보. 어떻게 만들지 감이 안잡히니까 일단 String으로 넣자.

    @Builder(builderClassName = "createRestaurant", builderMethodName = "createRestaurant")
    private Restaurant(String name, Location location, Double geoLocationX, Double geoLocationY, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, String adminComment, Integer minCost, String telNum) {
        validate(name, location, category, geoLocationX, geoLocationY, isAtmosphere, hasCostPerformance, canEatSingle, minCost, telNum);

        this.name = name;
        this.location = location;
        this.geoLocation = new GeoLocation(geoLocationX, geoLocationY);
        this.category = category;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.adminComment = adminComment;
        this.minCost = minCost;
        this.telNum = telNum;
    }

    private void validate(String name, Location location, FoodCategory category, Double geoLocationX, Double geoLocationY, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, Integer minCost, String telNum) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 누락되었습니다.");
        }
        validate(location, geoLocationX, geoLocationY, category, isAtmosphere, hasCostPerformance, canEatSingle, minCost, telNum);
    }

    public void update(Location location, Double geoLocationX, Double geoLocationY, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, String adminComment, Integer minCost, String telNum) {
        validate(location, geoLocationX, geoLocationY, category, isAtmosphere, hasCostPerformance, canEatSingle, minCost, telNum);

        this.location = location;
        this.category = category;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.adminComment = adminComment;
        this.minCost = minCost;
        this.telNum = telNum;
    }

    private void validate(Location location, Double geoLocationX, Double geoLocationY, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, Integer minCost, String telNum) {
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
        if (minCost == null) {
            throw new IllegalArgumentException("가격 관련 정보가 누락되었습니다.");
        }
        // TODO telNul을 matches를 사용해 검증하는 로직.
    }
}
