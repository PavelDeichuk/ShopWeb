package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.DiscountDto;
import com.pavel.shopweb.Entity.DiscountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class DiscountMapperTest {

    @MockBean
    private DiscountMapper discountMapper;

    @Test
    void DISCOUNT_DTO() {
        DiscountEntity discount = DiscountEntity.builder().id(1L).build();
        DiscountDto discountDto = discountMapper.INSTANCE.DISCOUNT_DTO(discount);
        assertEquals(discountDto.getId(), discount.getId());
    }
}