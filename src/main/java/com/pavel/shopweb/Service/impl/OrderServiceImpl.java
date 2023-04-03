package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderDetailEntity;
import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Enum.OrderStatus;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.OrderMapper;
import com.pavel.shopweb.Repository.OrderRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UsersRepository usersRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UsersRepository usersRepository) {
        this.orderRepository = orderRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<OrderDto> GetAllOrder(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<OrderEntity> orderEntities = orderRepository.findAll(pageable);
        if(orderEntities.isEmpty()){
            throw new BadRequestException("List order is empty!");
        }
        return orderEntities
                .stream()
                .map(OrderMapper.INSTANCE::ORDER_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto GetOrderById(Long order_id) {
        OrderEntity orderEntity = orderRepository
                .findById(order_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for order id!");
                });
        return OrderMapper.INSTANCE.ORDER_DTO(orderEntity);
    }

    @Override
    public OrderDto CreateOrder(Long user_id, OrderEntity orderEntity) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id!");
                });
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setOrderEntity(orderEntity);
        orderEntity.setOrderStatus(OrderStatus.NEW);
        orderEntity.setUsersEntity(users);
        orderEntity.setOrderDetailEntities(Arrays.asList(orderDetailEntity));
        OrderEntity orderSave = orderRepository.save(orderEntity);
        return OrderMapper.INSTANCE.ORDER_DTO(orderSave);
    }

    @Override
    public OrderDto EditOrder(Long order_id, OrderEntity orderEntity) {
        OrderEntity order = orderRepository
                .findById(order_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for order_id");
                });
        order.setName(orderEntity.getName());
        order.setSurname(orderEntity.getSurname());
        order.setAddress(orderEntity.getAddress());
        order.setPhone(orderEntity.getPhone());
        order.setAddress(orderEntity.getAddress());
        order.setOrderStatus(orderEntity.getOrderStatus());
        OrderEntity orderSave = orderRepository.save(orderEntity);
        return OrderMapper.INSTANCE.ORDER_DTO(orderSave);
    }

    @Override
    public OrderDto DeleteOrder(Long order_id) {
        OrderEntity order = orderRepository
                .findById(order_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for order_id");
                });
        orderRepository.deleteById(order_id);
        return OrderMapper.INSTANCE.ORDER_DTO(order);
    }
}
