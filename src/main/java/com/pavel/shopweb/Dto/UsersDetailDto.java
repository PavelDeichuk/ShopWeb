package com.pavel.shopweb.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDetailDto {
    private Long id;

    private String firstname;

    private String surname;

    private int age;

    private LocalDateTime birthday;

    private String description;
}
