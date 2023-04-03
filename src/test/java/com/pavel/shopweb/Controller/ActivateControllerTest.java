package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Service.ActivateService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ActivateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivateService activateService;

    @Test
    void createPromo() throws Exception {
        ActivateDto activateDto = new ActivateDto();
        BDDMockito.given(activateService.CreatePromo(1L,1L)).willReturn(activateDto);
        mockMvc
                .perform(get("/api/v1/activate/create/user/{user_id}/promo/{promo_id}", 1L, 1L)
                        .content(new ObjectMapper().writeValueAsBytes(activateDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void activatePromo() throws Exception {
        ActivateDto activateDto = new ActivateDto();
        BDDMockito.given(activateService.ActivatePromo(1L,1L)).willReturn(activateDto);
        mockMvc
                .perform(get("/api/v1/activate/user/{user_id}/promo/{promo_id}")
                        .content(new ObjectMapper().writeValueAsBytes(activateDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteActivate() throws Exception {
        ActivateDto activateDto = new ActivateDto();
        BDDMockito.given(activateService.DeleteActivate(1L)).willReturn(activateDto);
        mockMvc
                .perform(delete("/api/v1/activate/{activate_id}")
                        .content(new ObjectMapper().writeValueAsBytes(activateDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}