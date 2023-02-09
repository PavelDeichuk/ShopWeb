package com.pavel.shopweb.Dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {
    private Long id;

    private String number;

    private String fullName;

    private LocalDateTime validity;

    private String cvv;
}
