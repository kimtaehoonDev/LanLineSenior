package com.example.lanlineelderdemo.domain;

import lombok.Getter;

@Getter
public enum FoodCategory {
    KOREAN("한식"), CHINESE("중식"), WESTERN("양식"), JAPANESE("일식"), CHICKEN("치킨"), PORK_FEET("족발"), HAMBURGER("햄버거"), ASIAN("아시안"), BUNSIK("분식");

    private String name;

    FoodCategory(String name) {
        this.name = name;
    }
}
