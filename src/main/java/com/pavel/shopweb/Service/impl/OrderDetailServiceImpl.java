package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.OrderDetailDto;
import com.pavel.shopweb.Entity.OrderDetailEntity;
import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.OrderDetailMapper;
import com.pavel.shopweb.Repository.OrderDetailRepository;
import com.pavel.shopweb.Repository.OrderRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailDto EditOrderDetail(Long order_id, Long product_id) {
        OrderEntity order = orderRepository
                .findById(order_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for order_id!");
                });
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id!");
                });
        OrderDetailEntity orderDetail = orderDetailRepository.findByOrderEntity(order);
        orderDetail.setProductEntity(productEntity);
        orderDetail.setPrice(productEntity.getPrice());
        OrderDetailEntity orderSave = orderDetailRepository.save(orderDetail);
        return OrderDetailMapper.INSTANCE.ORDER_DETAIL_DTO(orderSave);
    }

    @Override
    public OrderDetailDto DeleteOrderDetail(Long order_detail_id) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository
                .findById(order_detail_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for order detail id!");
                });
        orderDetailRepository.deleteById(order_detail_id);
        return OrderDetailMapper.INSTANCE.ORDER_DETAIL_DTO(orderDetailEntity);
    }
}
