package com.pavel.shopweb.Dto;

import jakarta.persistence.Transient;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private Long price;

    private Long totalPrice;
}
