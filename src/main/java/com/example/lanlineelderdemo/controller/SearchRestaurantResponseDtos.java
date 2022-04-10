package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.service.dto.response.SearchRestaurantResponseDto;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class SearchRestaurantResponseDtos {
    List<SearchRestaurantResponseDto> searchRestaurantResponseDtos = new ArrayList<>();

    public SearchRestaurantResponseDtos(List<SearchRestaurantResponseDto> searchRestaurantResponseDtos) {
        for (SearchRestaurantResponseDto searchRestaurantResponseDto : searchRestaurantResponseDtos) {
            this.searchRestaurantResponseDtos.add(searchRestaurantResponseDto);
        }
    }
}
