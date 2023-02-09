package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.OrderDetailDto;
import com.pavel.shopweb.Entity.OrderDetailEntity;
import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Repository.OrderDetailRepository;
import com.pavel.shopweb.Repository.OrderRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class OrderDetailServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;

    @Test
    void editOrderDetail() {
        OrderEntity order = new OrderEntity();
        OrderDetailEntity orderDetail = OrderDetailEntity.builder().id(1L).build();
        ProductEntity productEntity = new ProductEntity();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(orderDetailRepository.findByOrderEntity(order)).thenReturn(orderDetail);
        when(orderDetailRepository.save(orderDetail)).thenReturn(orderDetail);
        OrderDetailDto orderDetailTest = orderDetailService.EditOrderDetail(1L,1L);
        assertEquals(orderDetailTest.getId(), orderDetail.getId());
    }
}