package com.example.lanlineelderdemo.web.form.review;

import com.example.lanlineelderdemo.review.dto.ReviewUpdateServiceRequestDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewUpdateForm {
    @NotBlank
    private String writerName;
    @NotBlank
    private String password;
    @NotBlank
    private String content;

//    public ReviewUpdateServiceRequestDto changeServiceDto() {
//        ReviewUpdateServiceRequestDto dto = new ReviewUpdateServiceRequestDto();
//        dto.setWriterName(writerName);
//        dto.setPassword(password);
//        dto.setContent(content);
//        return dto;
//    }
}
