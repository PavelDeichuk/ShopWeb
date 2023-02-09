package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;
import com.pavel.shopweb.Service.CreditCardService;
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
class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    @Test
    void getCreditCardByUserId() throws Exception{
        List<CreditCardDto> creditCardDtos = new ArrayList<>();
        BDDMockito.given(creditCardService.GetCreditCardByUser(1L))
                .willReturn(creditCardDtos);
        mockMvc
                .perform(get("/api/v1/credit/user/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(creditCardDtos))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createCreditCard() throws Exception{
        CreditCardDto creditCardDto = new CreditCardDto();
        CreditCardEntity creditCardEntity = new CreditCardEntity();
        BDDMockito.given(creditCardService.CreateCreditCard(1L, creditCardEntity))
                .willReturn(creditCardDto);
        mockMvc
                .perform(post("/api/v1/credit/user/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(creditCardDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editCreditCard() throws Exception{
        CreditCardDto creditCardDto = new CreditCardDto();
        CreditCardEntity creditCardEntity = new CreditCardEntity();
        BDDMockito.given(creditCardService.EditCreditCard(1L, creditCardEntity))
                .willReturn(creditCardDto);
        mockMvc
                .perform(put("/api/v1/credit/{credit_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(creditCardDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCreditCard() throws Exception{
        CreditCardDto creditCard = new CreditCardDto();
        BDDMockito.given(creditCardService.DeleteCreditCard(1L))
                .willReturn(creditCard);
        mockMvc
                .perform(delete("/api/v1/credit/{credit_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(creditCard))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}