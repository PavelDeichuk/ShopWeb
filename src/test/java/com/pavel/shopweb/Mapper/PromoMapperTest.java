package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class PromoMapperTest {
    @MockBean
    private PromoMapper promoMapper;

    @Test
    void PROMO_DTO() {
        PromoEntity promo = PromoEntity.builder().id(1L).build();
        PromoDto promoDto = promoMapper.INSTANCE.PROMO_DTO(promo);
        assertEquals(promoDto.getId(), promo.getId());
    }
}