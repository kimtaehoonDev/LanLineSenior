package com.example.lanlineelderdemo.service.menu;

import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RegisterMenuRequestServiceDto {
    @NotNull
    private String restaurantName;

    @NotNull
    private OpenType openType;

    @NotNull
    private String menuName;

    @NotNull
    private Integer numberOfMeal;

    @NotNull
    private Integer price;
}
