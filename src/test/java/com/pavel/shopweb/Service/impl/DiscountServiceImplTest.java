package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.DiscountDto;
import com.pavel.shopweb.Entity.DiscountEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Repository.DiscountRepository;
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
class DiscountServiceImplTest {

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DiscountServiceImpl discountService;

    @Test
    void getAllDiscount() {
        List<DiscountEntity> discountEntities = new ArrayList<>();
        discountEntities.add(null);
        PageImpl<DiscountEntity> pageImpl = new PageImpl<>(discountEntities);
        when(discountRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, discountService.GetAllDiscount(1, 3).size());
        verify(discountRepository).findAll((Pageable) any());
    }

    @Test
    void createDiscount() {
        ProductEntity productEntity = ProductEntity.builder().price(100L).build();
        DiscountEntity discount = DiscountEntity.builder().id(1L).price(10L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(discountRepository.save(discount)).thenReturn(discount);
        DiscountDto discountDto = discountService.CreateDiscount(1L, discount);
        assertEquals(discountDto.getId(), discount.getId());
    }

    @Test
    void editDiscount() {
        ProductEntity productEntity = ProductEntity.builder().id(1L).price(1000L).build();
        DiscountEntity discount = DiscountEntity.builder().id(1L).price(10L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(discountRepository.findById(1L)).thenReturn(Optional.of(discount));
        when(discountRepository.save(discount)).thenReturn(discount);
        DiscountDto discountDto = discountService.EditDiscount(1L,1L, discount);
        assertEquals(discountDto.getId(), discount.getId());
    }

}