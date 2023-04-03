package com.pavel.shopweb.Service;


import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderDto> GetAllOrder(int page, int size);

    OrderDto GetOrderById(Long order_id);

    OrderDto CreateOrder(Long user_id, OrderEntity orderEntity);

    OrderDto EditOrder(Long order_id, OrderEntity orderEntity);

    OrderDto DeleteOrder(Long order_id);
}
