package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.WishDto;
import com.pavel.shopweb.Entity.WishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WishMapper {
    WishMapper INSTANCE = Mappers.getMapper(WishMapper.class);

    WishDto WISH_DTO(WishEntity wishEntity);
}
