package com.example.lanlineelderdemo.review.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewUpdateServiceRequestDto {

    @NotBlank
    private String content;

    @NotBlank
    private String writerName;

    @NotBlank
    private String password;

//    public ReviewUpdateServiceRequestDto(String writerName, String password, String content) {
//        this.content = content;
//        this.writerName = writerName;
//        this.password = password;
//    }
}
