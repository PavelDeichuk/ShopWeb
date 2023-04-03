package com.pavel.shopweb.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;

    private String name;

    private String description;

    private int marks;

    private Long totalMarks;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
