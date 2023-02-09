package com.pavel.shopweb.Dto;

import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private Long id;

    private Long price;

    private OrderEntity orderEntity;

    private ProductEntity productEntity;
}
