package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Repository.PromoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class PromoServiceImplTest {

    @Mock
    private PromoRepository promoRepository;

    @InjectMocks
    private PromoServiceImpl promoService;

    @Test
    void getPromoByName() {
        PromoEntity promoEntity = PromoEntity.builder().id(1L).build();
        when(promoRepository.findByName("test")).thenReturn(Optional.of(promoEntity));
        PromoDto promoDto = promoService.GetPromoByName("test");
        assertEquals(promoDto.getId(), promoEntity.getId());
    }

    @Test
    void createPromo() {
        PromoEntity promoEntity = PromoEntity.builder().id(1L).build();
        when(promoRepository.findByName("test")).thenReturn(Optional.of(promoEntity));
        when(promoRepository.save(promoEntity)).thenReturn(promoEntity);
        PromoDto promoDto = promoService.CreatePromo(promoEntity);
        assertEquals(promoDto.getId(), promoEntity.getId());
    }

    @Test
    void editPromo() {
        PromoEntity promoEntity = PromoEntity.builder().id(1L).build();
        when(promoRepository.findById(1L)).thenReturn(Optional.of(promoEntity));
        when(promoRepository.findByName("test")).thenReturn(Optional.of(promoEntity));
        when(promoRepository.save(promoEntity)).thenReturn(promoEntity);
        PromoDto promoDto = promoService.EditPromo(1L, promoEntity);
        assertEquals(promoDto.getId(), promoEntity.getId());
    }
}