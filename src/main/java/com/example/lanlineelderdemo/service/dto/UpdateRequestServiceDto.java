package com.example.lanlineelderdemo.service.dto;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import lombok.Data;

@Data
public class UpdateRequestServiceDto {
    // 이름 변경은 update로 안되는거로. 만약 가게 이름이 바뀌면 다른 방법으로 이름을 바꿔주자.

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;

    private Integer maxCost;

}
