package com.pavel.shopweb.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Long id;

    private String username;

    private String email;

    private String role;

    private Boolean mfa;

    private Boolean mailing;

    private LocalDateTime createAt;
}
