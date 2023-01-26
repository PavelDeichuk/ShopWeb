package com.pavel.shopweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private MultipartFile multipartFile;

    @Test
    void getAllUsers() throws Exception{
        List<UsersDto> usersDtos = new ArrayList<>();
        BDDMockito.given(usersService.GetAllUsers(0,10)).willReturn(usersDtos);
        mockMvc
                .perform(get("/api/v1/users")
                        .content(new ObjectMapper().writeValueAsBytes(usersDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        UsersDto usersDto = new UsersDto();
        BDDMockito.given(usersService.GetUserById(1L)).willReturn(usersDto);
        mockMvc
                .perform(get("/api/v1/users/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception{
        UsersDto usersDto = new UsersDto();
        BDDMockito.given(usersService.CreateUser(any(UsersEntity.class), any())).willReturn(usersDto);
        mockMvc
                .perform(post("/api/v1/users")
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void activateAccountUser() throws Exception{
        UsersDto usersDto = new UsersDto();
        BDDMockito.given(usersService.ActivateUser("test")).willReturn(usersDto);
        mockMvc
                .perform(get("/api/v1/users/activate")
                        .param("token", "test")
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void GenerateQrCode_Success200() throws Exception {
        String qrCode = "test";
        BDDMockito.given(usersService.GenerateQrCode(anyString())).willReturn(qrCode);
        mockMvc
                .perform(get("/api/v1/users/qr")
                        .param("secret", "test")
                        .content(new ObjectMapper().writeValueAsBytes(qrCode))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void CreateImageUserTest() throws Exception {
        byte[] imageByte = new byte[0];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test", imageByte);
        BDDMockito.given(usersService.CreateImageUsers(1L, multipartFile)).willReturn(true);
        mockMvc
                .perform(multipart("/api/v1/users/{users_id}/image", 1L)
                        .file(mockMultipartFile)
                        .content(new ObjectMapper().writeValueAsBytes(true)))
                .andExpect(status().isOk());
    }

    @Test
    void EditImageUserTest() throws Exception {
        byte[] imageByte = new byte[0];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test", imageByte);
        BDDMockito.given(usersService.EditImageUsers(1L, 1L, multipartFile)).willReturn(true);
        mockMvc
                .perform(put("/api/v1/users/{users_id}/image/{image_id}", 1L, 1L)
                        .content(new ObjectMapper().writeValueAsBytes(true)))
                .andExpect(status().isOk());
    }

    @Test
    void resetPassword() throws Exception{
        UsersDto usersDto = new UsersDto();
        BDDMockito.given(usersService.ResetPassword("test")).willReturn(usersDto);
        mockMvc
                .perform(get("/api/v1/users/reset")
                        .param("email", "test")
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void changePassword() throws Exception{
        UsersDto usersDto = new UsersDto();
        UsersEntity users = new UsersEntity();
        BDDMockito.given(usersService.ChangePassword("test", users)).willReturn(usersDto);
        mockMvc
                .perform(post("/api/v1/users/change")
                        .param("token", "test")
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editUser() throws Exception{
        UsersDto usersDto = new UsersDto();
        UsersEntity users = new UsersEntity();
        BDDMockito.given(usersService.EditUser(1L, users)).willReturn(usersDto);
        mockMvc
                .perform(put("/api/v1/users/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception{
        UsersDto usersDto = new UsersDto();
        BDDMockito.given(usersService.DeleteUser(1L)).willReturn(usersDto);
        mockMvc
                .perform(delete("/api/v1/users/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(usersDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}