package com.example.lanlineelderdemo;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.utils.interceptor.AdminCheckInterceptor;
import com.example.lanlineelderdemo.utils.enums.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("locations", Location.class);
        enumMapper.put("foodCategories", FoodCategory.class);
        enumMapper.put("openTypes", OpenType.class);
        return enumMapper;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminCheckInterceptor())
                .order(1)
                .addPathPatterns("/admin","/restaurants/new", "/menu/new", "/restaurants","/menu");
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}