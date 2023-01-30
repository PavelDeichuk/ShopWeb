package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.CategoryDto;
import com.pavel.shopweb.Entity.CategoryEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Repository.CategoryRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void getAllCategories() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(null);
        PageImpl<CategoryEntity> pageImpl = new PageImpl<>(categoryEntities);
        when(categoryRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, categoryService.GetAllCategories(1, 3).size());
        verify(categoryRepository).findAll((Pageable) any());
    }

    @Test
    void createCategory() {
        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).build();
        when(categoryRepository.findByName("test")).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        CategoryDto category = categoryService.CreateCategory(categoryEntity);
        assertEquals(category.getId(), categoryEntity.getId());
    }

    @Test
    void addProductToCategory() {
        ProductEntity productEntity = ProductEntity.builder().id(1L).build();
        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
    }

    @Test
    void editCategory() {
        CategoryEntity category = CategoryEntity.builder().id(1L).build();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        CategoryDto categoryDto = categoryService.EditCategory(1L, category);
        assertEquals(categoryDto.getId(), category.getId());
    }

}