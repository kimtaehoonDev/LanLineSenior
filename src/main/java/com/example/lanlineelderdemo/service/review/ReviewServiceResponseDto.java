package com.example.lanlineelderdemo.service.review;

import com.example.lanlineelderdemo.domain.Review;

public class ReviewServiceResponseDto {
    private Long id;
    private String writerName;
    private String content;

    public ReviewServiceResponseDto(Long id, String writerName, String content) {
        this.id = id;
        this.writerName = writerName;
        this.content = content;
    }

    public static ReviewServiceResponseDto from(Review review) {
        return new ReviewServiceResponseDto(
                review.getId(), review.getWriterName(), review.getContent());

    }
}
