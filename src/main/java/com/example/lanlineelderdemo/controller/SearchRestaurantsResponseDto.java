package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.service.dto.response.SearchRestaurantResponseDto;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
public class SearchRestaurantsResponseDto {
    private List<SearchRestaurantResponseDto> searchRestaurantResponseDtos = new ArrayList<>();

    public SearchRestaurantsResponseDto(List<SearchRestaurantResponseDto> searchRestaurantResponseDtos) {
        for (SearchRestaurantResponseDto searchRestaurantResponseDto : searchRestaurantResponseDtos) {
            this.searchRestaurantResponseDtos.add(searchRestaurantResponseDto);
        }
    }

    public List<SearchRestaurantResponseDto> getSearchRestaurantResponseDtos() {
        return Collections.unmodifiableList(searchRestaurantResponseDtos);
        // TODO 굳이 수정안되는 이 처리를 해줘야 하나?
    }
}
