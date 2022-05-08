package com.example.lanlineelderdemo.review.dto;

import lombok.Data;

@Data
public class ReviewUpdateServiceRequestDto {

    private String content;

    private String writerName;

    private String password;
}
