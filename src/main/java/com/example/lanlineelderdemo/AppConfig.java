package com.example.lanlineelderdemo;

import com.example.lanlineelderdemo.domain.EnumMapper;
import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("locations", Location.class);
        enumMapper.put("foodCategories", FoodCategory.class);
        return enumMapper;
    }
}