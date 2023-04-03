package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto ORDER_DTO(OrderEntity orderEntity);
}
