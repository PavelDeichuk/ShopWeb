package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.OrderDetailDto;
import com.pavel.shopweb.Entity.OrderDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    OrderDetailDto ORDER_DETAIL_DTO(OrderDetailEntity orderDetailEntity);
}
