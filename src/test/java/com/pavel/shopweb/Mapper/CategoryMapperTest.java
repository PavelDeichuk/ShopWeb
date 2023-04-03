package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.CategoryDto;
import com.pavel.shopweb.Entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class CategoryMapperTest {

    @MockBean
    private CategoryMapper categoryMapper;

    @Test
    void CATEGORY_DTO() {
        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).build();
        CategoryDto categoryDto = categoryMapper.INSTANCE.CATEGORY_DTO(categoryEntity);
        assertEquals(categoryDto.getId(), categoryEntity.getId());
    }
}