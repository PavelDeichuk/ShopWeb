package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.OrderDetailDto;
import com.pavel.shopweb.Entity.OrderDetailEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class OrderDetailMapperTest {

    @MockBean
    private OrderDetailMapper orderDetailMapper;
    @Test
    void ORDER_DETAIL_DTO() {
        OrderDetailEntity orderDetailEntity = OrderDetailEntity.builder().id(1L).build();
        OrderDetailDto orderDetailDto = orderDetailMapper.INSTANCE.ORDER_DETAIL_DTO(orderDetailEntity);
        assertEquals(orderDetailDto.getId(), orderDetailEntity.getId());
    }
}