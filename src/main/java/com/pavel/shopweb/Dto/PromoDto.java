package com.pavel.shopweb.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoDto {
    private Long id;

    private String name;

    private Long price;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private int activates;
}
