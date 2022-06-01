package com.example.lanlineelderdemo.domain.restaurant;

import com.example.lanlineelderdemo.utils.enums.EnumModel;

import java.util.Arrays;

public enum Location implements EnumModel {
    FRONT_DOOR("전대정문"), BACK_DOOR("전대후문"), SANGDAE("상대"),
    ENGINEER_SIDE_DOOR("공대쪽문"), BOKGAE("복개도로"), INSIDE_SCHOOL("학교내부");

    private String value;

    Location(String value) {
        this.value = value;
    }

    public static Location find(String key) {
        return Arrays.stream(Location.values()).filter(location -> location.getKey().equals(key)).findAny().orElseThrow(IllegalArgumentException::new);
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
