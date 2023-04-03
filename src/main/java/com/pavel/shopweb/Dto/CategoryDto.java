package com.pavel.shopweb.Dto;

import com.pavel.shopweb.Entity.ProductEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    private String name;

    private List<ProductEntity> productEntities;
}
