package com.pavel.shopweb.Dto;

import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateDto {
    private Long id;

    private Long activates;

    private UsersEntity usersEntity;

    private PromoEntity promoEntity;
}
