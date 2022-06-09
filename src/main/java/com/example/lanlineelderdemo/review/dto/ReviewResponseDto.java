package com.example.lanlineelderdemo.review.dto;

import com.example.lanlineelderdemo.domain.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ReviewResponseDto {
    private Long id;
    private String writerName;
    private String content;
    private String lastModifiedDate;

    public ReviewResponseDto(Long id, String writerName, String content, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.writerName = writerName;
        this.content = content;
        //TODO LocalDateTime의 형식을 변경해주는 로직. 여기 말고 다른 위치로 옮기고 싶음. 고민 한 번 해보기.
        this.lastModifiedDate = lastModifiedDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd a HH:mm:ss"));

    }

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getId(), review.getWriterName(), review.getContent(), review.getLastModifiedDate());

    }
}
