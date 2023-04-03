package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.OrderDetailDto;
import com.pavel.shopweb.Service.OrderDetailService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    private static final String ORDER_DETAIL_CREATE = "/order/{order_id}/product/{product_id}";

    private static final String ORDER_DETAIL_ID = "/{order_detail_id}";

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping(value = ORDER_DETAIL_CREATE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailDto CreateOrderDetail(@PathVariable Long order_id,
                                            @PathVariable Long product_id){
        return orderDetailService.EditOrderDetail(order_id, product_id);
    }

    @RequestMapping(value = ORDER_DETAIL_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailDto DeleteOrderDetailId(@PathVariable Long order_detail_id){
        return orderDetailService.DeleteOrderDetail(order_detail_id);
    }
}
