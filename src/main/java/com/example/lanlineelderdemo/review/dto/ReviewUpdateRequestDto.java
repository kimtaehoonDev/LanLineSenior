package com.example.lanlineelderdemo.review.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewUpdateRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String content;

    public ReviewUpdateServiceRequestDto changeServiceDto() {
        ReviewUpdateServiceRequestDto dto = new ReviewUpdateServiceRequestDto();
        dto.setWriterName(name);
        dto.setPassword(password);
        dto.setContent(content);
        return dto;
    }
}
