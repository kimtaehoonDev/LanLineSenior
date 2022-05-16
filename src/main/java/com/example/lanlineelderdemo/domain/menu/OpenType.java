package com.example.lanlineelderdemo.domain.menu;

import com.example.lanlineelderdemo.utils.enums.EnumModel;

import java.util.Arrays;

public enum OpenType implements EnumModel {
    LUNCH("점심"), DINNER("저녁"), BOTH("둘다");

    private String value;

    OpenType(String value) {
        this.value = value;
    }

    public static OpenType find(String key) {
        return Arrays.stream(OpenType.values()).filter(openType ->
                        openType.getKey().equals(key)).findAny().orElseThrow(IllegalArgumentException::new);
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
