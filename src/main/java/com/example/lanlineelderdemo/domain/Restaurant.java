package com.example.lanlineelderdemo.domain;

import lombok.*;

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

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;

    private Integer maxCost;

    @Builder(builderClassName = "createRestaurant", builderMethodName = "createRestaurant")
    private Restaurant(String name, Location location, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, String adminComment, Integer minCost, Integer maxCost) {
        validate(name, location, category, isAtmosphere, hasCostPerformance, canEatSingle, adminComment, minCost, maxCost);

        this.name = name;
        this.location = location;
        this.category = category;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.adminComment = adminComment;
        this.minCost = minCost;
        this.maxCost = maxCost;
    }

    private void validate(String name, Location location, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, String adminComment, Integer minCost, Integer maxCost) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 누락되었습니다.");
        }
        if (location == null) {
            throw new IllegalArgumentException("위치 정보가 누락되었습니다.");
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
        if (adminComment == null) {
            throw new IllegalArgumentException("한줄 코멘트가 누락되었습니다.");
        }
        if (minCost == null || maxCost == null) {
            throw new IllegalArgumentException("가격 관련 정보가 누락되었습니다.");
        }
    }

    public void update(Location location, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, String adminComment, Integer minCost, Integer maxCost) {
        validate(location, category, isAtmosphere, hasCostPerformance, canEatSingle, adminComment, minCost, maxCost);

        this.location = location;
        this.category = category;
        this.isAtmosphere = isAtmosphere;
        this.hasCostPerformance = hasCostPerformance;
        this.canEatSingle = canEatSingle;
        this.adminComment = adminComment;
        this.minCost = minCost;
        this.maxCost = maxCost;
    }

    private void validate(Location location, FoodCategory category, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, String adminComment, Integer minCost, Integer maxCost) {
        if (location == null) {
            throw new IllegalArgumentException("위치 정보가 누락되었습니다.");
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
        if (adminComment == null) {
            throw new IllegalArgumentException("한줄 코멘트가 누락되었습니다.");
        }
        if (minCost == null || maxCost == null) {
            throw new IllegalArgumentException("가격 관련 정보가 누락되었습니다.");
        }
    }
}
