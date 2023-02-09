package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.OrderDto;
import com.pavel.shopweb.Entity.OrderEntity;
import com.pavel.shopweb.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void getAllOrders() throws Exception{
        List<OrderDto> orderDtos = new ArrayList<>();
        BDDMockito.given(orderService.GetAllOrder(0,10)).willReturn(orderDtos);
        mockMvc
                .perform(get("/api/v1/order")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsBytes(orderDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderByID() throws Exception{
        OrderDto orderDto = new OrderDto();
        BDDMockito.given(orderService.GetOrderById(1L)).willReturn(orderDto);
        mockMvc
                .perform(get("/api/v1/order/{order_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(orderDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder() throws Exception{
        OrderEntity order = new OrderEntity();
        OrderDto orderDto = new OrderDto();
        BDDMockito.given(orderService.CreateOrder(1L, order)).willReturn(orderDto);
        mockMvc
                .perform(post("/api/v1/order/user/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(orderDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editOrder() throws Exception{
        OrderEntity order = new OrderEntity();
        OrderDto orderDto = new OrderDto();
        BDDMockito.given(orderService.EditOrder(1L, order)).willReturn(orderDto);
        mockMvc
                .perform(put("/api/v1/order/{order_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(orderDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrder() throws Exception{
        OrderDto orderDto = new OrderDto();
        BDDMockito.given(orderService.DeleteOrder(1L)).willReturn(orderDto);
        mockMvc
                .perform(delete("/api/v1/order/{order_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(orderDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}