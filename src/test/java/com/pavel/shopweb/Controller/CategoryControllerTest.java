package com.pavel.shopweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.shopweb.Dto.CategoryDto;
import com.pavel.shopweb.Entity.CategoryEntity;
import com.pavel.shopweb.Service.CategoryService;
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

import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getAllCategory() throws Exception{
        List<CategoryDto> categoryDtos = new ArrayList<>();
        BDDMockito.given(categoryService.GetAllCategories(0,10)).willReturn(categoryDtos);
        mockMvc
                .perform(get("/api/v1/category")
                        .content(new ObjectMapper().writeValueAsBytes(categoryDtos))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createCategory() throws Exception{
        CategoryDto categoryDto = new CategoryDto();
        CategoryEntity categoryEntity = new CategoryEntity();
        BDDMockito.given(categoryService.CreateCategory(categoryEntity)).willReturn(categoryDto);
        mockMvc
                .perform(post("/api/v1/category")
                        .content(new ObjectMapper().writeValueAsBytes(categoryDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void addProductToCategory() throws Exception{
        boolean answer = true;
        BDDMockito.given(categoryService.AddProductToCategory(1L, 1L)).willReturn(answer);
        mockMvc
                .perform(get("/api/v1/category/product/{product_id}/category/{category_id}", 1L, 1L)
                        .content(new ObjectMapper().writeValueAsBytes(answer))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editCategory() throws Exception{
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryDto categoryDto = new CategoryDto();
        BDDMockito.given(categoryService.EditCategory(1L, categoryEntity)).willReturn(categoryDto);
        mockMvc
                .perform(put("/api/v1/category/{category_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(categoryDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        BDDMockito.given(categoryService.DeleteCategory(1L)).willReturn(categoryDto);
        mockMvc
                .perform(delete("/api/v1/category/{category_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(categoryDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}