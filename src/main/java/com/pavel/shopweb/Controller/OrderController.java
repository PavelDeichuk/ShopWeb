package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private static final String ORDER_ID = "/{order_id}";

    private static final String USER_ID = "/user/{user_id}";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> GetAllOrders(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size){
        return orderService.GetAllOrder(page, size);
    }

    @RequestMapping(value = ORDER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto GetOrderByID(@PathVariable Long order_id){
        return orderService.GetOrderById(order_id);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto CreateOrder(@PathVariable Long user_id,
                                @RequestBody OrderEntity order){
        return orderService.CreateOrder(user_id, order);
    }

    @RequestMapping(value = ORDER_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto EditOrder(@PathVariable Long order_id,
                              @RequestBody OrderEntity orderEntity){
        return orderService.EditOrder(order_id, orderEntity);
    }

    @RequestMapping(value = ORDER_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto DeleteOrder(@PathVariable Long order_id){
        return orderService.DeleteOrder(order_id);
    }
}
