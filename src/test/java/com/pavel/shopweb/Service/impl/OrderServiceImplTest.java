package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.OrderRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void getAllOrder() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(null);
        PageImpl<OrderEntity> pageImpl = new PageImpl<>(orderEntities);
        when(orderRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, orderService.GetAllOrder(1, 3).size());
        verify(orderRepository).findAll((Pageable) any());
    }

    @Test
    void getOrderById() {
        OrderEntity order = OrderEntity.builder().id(1L).build();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        OrderDto orderDto = orderService.GetOrderById(1L);
        assertEquals(orderDto.getId(), order.getId());
    }

    @Test
    void createOrder() {
        OrderEntity order = OrderEntity.builder().id(1L).build();
        UsersEntity users = UsersEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(orderRepository.save(order)).thenReturn(order);
        OrderDto orderDto = orderService.CreateOrder(1L, order);
        assertEquals(orderDto.getId(), order.getId());
    }

    @Test
    void editOrder() {
        OrderEntity order = OrderEntity.builder().id(1L).build();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);
        OrderDto orderDto = orderService.EditOrder(1L, order);
        assertEquals(orderDto.getId(), order.getId());
    }
}