package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Service.PromoService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PromoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromoService promoService;

    @Test
    void getPromoByName() throws Exception {
        PromoDto promoDto = new PromoDto();
        BDDMockito.given(promoService.GetPromoByName("test")).willReturn(promoDto);
        mockMvc
                .perform(get("/api/v1/promo/{promo_name}")
                        .content(new ObjectMapper().writeValueAsBytes(promoDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createPromo() throws Exception {
        PromoDto promoDto = new PromoDto();
        PromoEntity promoEntity = new PromoEntity();
        BDDMockito.given(promoService.CreatePromo(promoEntity)).willReturn(promoDto);
        mockMvc
                .perform(post("/api/v1/promo")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsBytes(promoDto)))
                .andExpect(status().isOk());
    }

    @Test
    void editPromo() throws Exception {
        PromoDto promoDto = new PromoDto();
        PromoEntity promo = new PromoEntity();
        BDDMockito.given(promoService.EditPromo(1L, promo)).willReturn(promoDto);
        mockMvc
                .perform(put("/api/v1/promo/{promo_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(promoDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePromo() throws Exception {
        PromoDto promoDto = new PromoDto();
        BDDMockito.given(promoService.DeletePromo(1L)).willReturn(promoDto);
        mockMvc
                .perform(delete("/api/v1/promo/{promo_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(promoDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}