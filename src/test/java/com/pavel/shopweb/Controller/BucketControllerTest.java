package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Service.BucketService;
import jakarta.servlet.http.HttpSession;
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
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BucketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BucketService bucketService;

    @MockBean
    private HttpSession httpSession;

    @Test
    void getProductByBucket() throws Exception {
        List<ProductDto> productEntities = new ArrayList<>();
        BDDMockito.given(bucketService.GetProductByBucket(1L, httpSession))
                .willReturn(productEntities);
        mockMvc
                .perform(get("/api/v1/bucket/user/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(productEntities))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void addProductByBucket() throws Exception{
        BucketDto bucketDto = new BucketDto();
        BDDMockito.given(bucketService.AddProductToBucket(1L, 1L, httpSession))
                .willReturn(bucketDto);
        mockMvc
                .perform(get("/api/v1/bucket/user/{user_id}/product/{product_id}", 1L, 1L)
                        .content(new ObjectMapper().writeValueAsString(bucketDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createBucketUser() throws Exception {
        BucketDto bucketDto = new BucketDto();
        BDDMockito.given(bucketService.CreateBucketUser(1L)).willReturn(bucketDto);
        mockMvc
                .perform(get("/api/v1/bucket/create/user/{user_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(bucketDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBucket() throws Exception{
        BucketDto bucketDto = new BucketDto();
        BDDMockito.given(bucketService.DeleteBucket(1L)).willReturn(bucketDto);
        mockMvc
                .perform(delete("/api/v1/bucket/{bucket_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(bucketDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}