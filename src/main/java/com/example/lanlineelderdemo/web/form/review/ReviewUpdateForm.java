package com.example.lanlineelderdemo.web.form.review;

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

}
