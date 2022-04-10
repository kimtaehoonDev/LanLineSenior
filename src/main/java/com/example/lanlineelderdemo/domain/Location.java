package com.example.lanlineelderdemo.domain;

public enum Location implements EnumModel {
    BACK_DOOR("전대후문"), FRONT_DOOR("전대정문"), SANGDAE("상대"),
    ENGINEER_SIDE_DOOR("공대쪽문"), BOKGAE("복개도로"), INSIDE_SCHOOL("학교내부"),
    JUNCHEOULWOO("전철우사거리");

    private String value;

    Location(String value) {
        this.value = value;
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
