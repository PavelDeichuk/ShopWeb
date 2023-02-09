package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class CreditCardMapperTest {

    @MockBean
    private CreditCardMapper creditCardMapper;

    @Test
    void creditCardMapperDto(){
        CreditCardEntity creditCard = CreditCardEntity.builder().id(1L).build();
        CreditCardDto creditCardDto = creditCardMapper.INSTANCE.CREDIT_CARD_DTO(creditCard);
        assertEquals(creditCardDto.getId(), creditCard.getId());
    }

}