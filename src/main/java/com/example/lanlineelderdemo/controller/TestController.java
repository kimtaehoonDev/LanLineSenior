package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.EnumModel;
import com.example.lanlineelderdemo.domain.EnumValue;
import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @GetMapping("/enum")
    public Map<String, Object> getEnum() {
        Map<String, Object> enums = new LinkedHashMap<>();

        Class location = Location.class;
        Class foodCategory = FoodCategory.class;

        enums.put("location", location.getEnumConstants());
        enums.put("foodCategory", foodCategory.getEnumConstants());
        return enums;
    }

    @GetMapping("/value")
    public Map<String, List<EnumValue>> getEnumValue() {
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();

        enumValues.put("location", toEnumValues(Location.class));
        enumValues.put("foodCategory", toEnumValues(FoodCategory.class));

        return enumValues;
    }

    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e){
        return Arrays
                .stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
    }

}
