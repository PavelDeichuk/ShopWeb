package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.DiscountDto;
import com.pavel.shopweb.Entity.DiscountEntity;
import com.pavel.shopweb.Service.DiscountService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountService discountService;

    @Test
    void getAllDiscount() throws Exception{
        List<DiscountDto> discountDtos = new ArrayList<>();
        BDDMockito.given(discountService.GetAllDiscount(0,10)).willReturn(discountDtos);
        mockMvc
                .perform(get("/api/v1/discount")
                        .content(new ObjectMapper().writeValueAsBytes(discountDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void createDiscount() throws Exception{
        DiscountDto discountDto = new DiscountDto();
        DiscountEntity discountEntity = new DiscountEntity();
        BDDMockito.given(discountService.CreateDiscount(1L, discountEntity))
                .willReturn(discountDto);
        mockMvc
                .perform(post("/api/v1/discount/product/{product_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(discountDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editDiscount() throws Exception{
        DiscountDto discountDto = new DiscountDto();
        DiscountEntity discountEntity = new DiscountEntity();
        BDDMockito.given(discountService.EditDiscount(1L, 1L, discountEntity))
                .willReturn(discountDto);
        mockMvc
                .perform(put("/api/v1/discount/{discount_id}/product/{product_id}",
                        1L, 1L)
                        .content(new ObjectMapper().writeValueAsBytes(discountDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDiscount() throws Exception{
        DiscountDto discountDto = new DiscountDto();
        BDDMockito.given(discountService.DeleteDiscount(1L)).willReturn(discountDto);
        mockMvc
                .perform(delete("/api/v1/discount/{discount_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(discountDto)))
                .andExpect(status().isOk());
    }
}