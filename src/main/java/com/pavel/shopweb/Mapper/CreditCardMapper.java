package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditCardMapper {
    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardDto CREDIT_CARD_DTO(CreditCardEntity creditCardEntity);
}
