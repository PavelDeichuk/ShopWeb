package com.pavel.shopweb.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavel.shopweb.Entity.UsersEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {
    private Long id;
    private String session;

    private int quantity;

    private Long price;

    @JsonIgnore
    private UsersEntity usersEntity;
}
