package com.pavel.shopweb.Model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
