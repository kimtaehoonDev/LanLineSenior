package com.example.lanlineelderdemo.review.dto;

import lombok.Data;

@Data
public class ReviewUpdateServiceRequestDto {

    private String content;

    private String writerName;

    private String password;

//    public ReviewUpdateServiceRequestDto(String writerName, String password, String content) {
//        this.content = content;
//        this.writerName = writerName;
//        this.password = password;
//    }
}
