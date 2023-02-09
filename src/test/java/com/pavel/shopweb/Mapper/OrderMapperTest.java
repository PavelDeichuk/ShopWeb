package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class OrderMapperTest {

    @MockBean
    private OrderMapper orderMapper;

    @Test
    void ORDER_DTO() {
        OrderEntity order = OrderEntity.builder().id(1L).build();
        OrderDto orderDto = orderMapper.INSTANCE.ORDER_DTO(order);
        assertEquals(orderDto.getId(), order.getId());
    }
}