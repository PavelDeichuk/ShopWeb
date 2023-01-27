package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto PRODUCT_DTO(ProductEntity productEntity);
}
