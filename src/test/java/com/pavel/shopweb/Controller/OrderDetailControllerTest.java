package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.OrderDetailDto;
import com.pavel.shopweb.Service.OrderDetailService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OrderDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderDetailService orderDetailService;

    @Test
    void createOrderDetail() throws Exception{
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        BDDMockito.given(orderDetailService.EditOrderDetail(1L, 1L))
                .willReturn(orderDetailDto);
        mockMvc
                .perform(get("/api/v1/order-detail/order/{order_id}/product/{product_id}")
                        .content(new ObjectMapper().writeValueAsBytes(orderDetailDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrderDetailId() throws Exception{
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        BDDMockito.given(orderDetailService.DeleteOrderDetail(1L))
                .willReturn(orderDetailDto);
        mockMvc
                .perform(delete("/api/v1/order-detail/{order_detail_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(orderDetailDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }
}