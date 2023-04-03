package com.pavel.shopweb.Dto;

import com.pavel.shopweb.Entity.UsersEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishDto {
    private Long id;

    private String session;

    private UsersEntity usersEntity;
}
