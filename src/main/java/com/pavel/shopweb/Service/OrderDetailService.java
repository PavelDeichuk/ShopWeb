package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.OrderDetailDto;

public interface OrderDetailService {
    OrderDetailDto EditOrderDetail(Long order_id, Long product_id);

    OrderDetailDto DeleteOrderDetail(Long order_detail_id);
}
