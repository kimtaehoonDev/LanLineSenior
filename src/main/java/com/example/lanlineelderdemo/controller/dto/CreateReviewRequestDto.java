package com.example.lanlineelderdemo.controller.dto;

import com.example.lanlineelderdemo.service.review.CreateReviewRequestServiceDto;
import lombok.Data;

@Data
public class CreateReviewRequestDto {
    private String name;
    private String password;
    private String content;

}
