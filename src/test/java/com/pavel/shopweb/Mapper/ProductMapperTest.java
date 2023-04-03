package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ProductMapperTest {

    @MockBean
    private ProductMapper productMapper;

    @Test
    void PRODUCT_DTO() {
        ProductEntity product = ProductEntity.builder().id(1L).build();
        ProductDto productDto = productMapper.INSTANCE.PRODUCT_DTO(product);
        assertEquals(productDto.getId(), product.getId());
    }
}