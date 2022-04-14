package com.example.lanlineelderdemo.domain;

import lombok.Getter;

import java.util.Arrays;

public enum FoodCategory implements EnumModel {
    KOREAN("한식"), CHINESE("중식"), WESTERN("양식"), JAPANESE("일식"), CHICKEN("치킨"), FAST_FOOD("패스트푸드"), ASIAN("아시안"), BUNSIK("분식"), ETC("기타");

    private String value;

    FoodCategory(String value) {
        this.value = value;
    }

    public static FoodCategory find(String key) {
        return Arrays.stream(FoodCategory.values()).filter(foodCategory -> foodCategory.getKey().equals(key)).findAny().orElseThrow(IllegalArgumentException::new);
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
