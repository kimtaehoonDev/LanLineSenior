package com.example.lanlineelderdemo.service.review;

import com.example.lanlineelderdemo.domain.Review;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.Data;

@Data
public class UpdateReviewRequestServiceDto {

    private String content;

    private String writerName;

    private String password;
}
