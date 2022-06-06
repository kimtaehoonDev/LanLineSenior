package com.example.lanlineelderdemo.domain.restaurant;

import com.example.lanlineelderdemo.utils.enums.EnumModel;

import java.util.Arrays;

public enum FoodCategory implements EnumModel {
    KOREAN("한식"), CHINESE("중식"), JAPANESE("일식"),
    ITALY("양식"), ASIAN("아시안"), CHICKEN("치킨")
    , FAST_FOOD("패스트푸드"), GOGI("고기"), BUNSIK("분식"), ETC("기타");

    private String value;

    FoodCategory(String value) {
        this.value = value;
    }

    public static FoodCategory find(String key) {
        return Arrays.stream(FoodCategory.values()).filter(foodCategory ->
                foodCategory.getKey().equals(key)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
