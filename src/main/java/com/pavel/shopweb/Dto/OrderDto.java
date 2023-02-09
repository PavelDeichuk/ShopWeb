package com.pavel.shopweb.Dto;

import com.pavel.shopweb.Enum.OrderStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private String name;

    private String surname;

    private String phone;

    private BigDecimal sum;
    private String address;

    private OrderStatus orderStatus;
}
