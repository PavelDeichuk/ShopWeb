package com.pavel.shopweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Service.UsersDetailService;
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
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UsersDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersDetailService usersDetailService;

    @MockBean
    private UsersDetailEntity usersDetailEntity;

    @MockBean
    private BindingResult bindingResult;


    @Test
    void getUsersDetailByIdTest() throws Exception{
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        BDDMockito.given(usersDetailService.GetUserDetailById(1L)).willReturn(usersDetailDto);
        mockMvc
                .perform(get("/api/v1/users-detail/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(usersDetailDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editUserDetailTest() throws Exception{
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        BDDMockito.given(usersDetailService.EditUserDetailById(1L, usersDetailEntity, bindingResult)).willReturn(usersDetailDto);
        mockMvc
                .perform(put("/api/v1/users-detail/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(usersDetailDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserDetailTest() throws Exception{
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        BDDMockito.given(usersDetailService.DeleteUserDetailById(1L)).willReturn(usersDetailDto);
        mockMvc
                .perform(delete("/api/v1/users-detail/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(usersDetailDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}