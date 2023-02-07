package com.pavel.shopweb.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavel.shopweb.Entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private Long id;

    private Long price;

    @JsonIgnore
    private ProductEntity productEntity;
}
