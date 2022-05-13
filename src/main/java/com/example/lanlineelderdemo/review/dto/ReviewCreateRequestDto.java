package com.example.lanlineelderdemo.review.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewCreateRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String content;

}
