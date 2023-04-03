package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromoMapper {
    PromoMapper INSTANCE = Mappers.getMapper(PromoMapper.class);

    PromoDto PROMO_DTO(PromoEntity promoEntity);
}
