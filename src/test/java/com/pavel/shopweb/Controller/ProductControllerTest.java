package com.pavel.shopweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Service.ProductService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private MultipartFile multipartFile;

    @Test
    void getAllProduct() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        BDDMockito.given(productService.GetAllProduct(0,10)).willReturn(productDtos);
        mockMvc
                .perform(get("/api/v1/product")
                        .content(new ObjectMapper().writeValueAsBytes(productDtos))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() throws Exception {
        ProductDto productDto = new ProductDto();
        BDDMockito.given(productService.GetProductById(1L)).willReturn(productDto);
        mockMvc
                .perform(get("/api/v1/product/{product_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(productDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void GetProductByCategory() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        BDDMockito.given(productService.GetProductByCategoryName("test")).willReturn(productDtos);
        mockMvc
                .perform(get("/api/v1/product/category")
                        .param("name", "test")
                        .content(new ObjectMapper().writeValueAsBytes(productDtos))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        ProductEntity product = new ProductEntity();
        BDDMockito.given(productService.CreateProduct(product)).willReturn(productDto);
        mockMvc
                .perform(post("/api/v1/product")
                        .content(new ObjectMapper().writeValueAsBytes(productDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createImageProduct() throws Exception{
        ProductDto productDto = new ProductDto();
        byte[] imageByte = new byte[0];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test", imageByte);
        BDDMockito.given(productService.CreateImageProduct(1L, multipartFile)).willReturn(productDto);
        mockMvc
                .perform(multipart("/api/v1/product/{product_id}/image", 1L)
                        .file(mockMultipartFile)
                        .content(new ObjectMapper().writeValueAsBytes(productDto)))
                .andExpect(status().isOk());
    }

    @Test
    void editImageProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        BDDMockito.given(productService.EditImageProduct(1L, multipartFile)).willReturn(productDto);
        mockMvc
                .perform(put("/api/v1/product/{product_id}/image", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(productDto)))
                .andExpect(status().isOk());
    }

    @Test
    void editProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        ProductEntity product = new ProductEntity();
        BDDMockito.given(productService.EditProduct(1L, product)).willReturn(productDto);
        mockMvc
                .perform(put("/api/v1/product/{product_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(productDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        BDDMockito.given(productService.DeleteProduct(1L)).willReturn(productDto);
        mockMvc
                .perform(delete("/api/v1/product/{product_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(productDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}