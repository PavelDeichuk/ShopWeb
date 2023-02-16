package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.WishDto;
import com.pavel.shopweb.Entity.WishEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class WishMapperTest {

    @MockBean
    private WishMapper wishMapper;

    @Test
    void WISH_DTO() {
        WishEntity wishEntity = WishEntity.builder().id(1L).build();
        WishDto wishDto = wishMapper.INSTANCE.WISH_DTO(wishEntity);
        assertEquals(wishDto.getId(), wishEntity.getId());
    }
}