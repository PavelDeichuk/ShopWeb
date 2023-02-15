package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Entity.ActivateEntity;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.ActivateRepository;
import com.pavel.shopweb.Repository.PromoRepository;
import com.pavel.shopweb.Repository.UsersRepository;
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
class ActivateServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PromoRepository promoRepository;

    @Mock
    private ActivateRepository activateRepository;

    @InjectMocks
    private ActivateServiceImpl activateService;

    @Test
    void createPromo() {
        UsersEntity users = new UsersEntity();
        PromoEntity promo = new PromoEntity();
        ActivateEntity activateEntity = ActivateEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(promoRepository.findById(1L)).thenReturn(Optional.of(promo));
        when(activateRepository.save(activateEntity)).thenReturn(activateEntity);
        ActivateDto activateDto = activateService.CreatePromo(1L, 1L);
        assertEquals(activateDto.getId(), activateEntity.getId());
    }

    @Test
    void activatePromo() {
        UsersEntity users = new UsersEntity();
        PromoEntity promo = new PromoEntity();
        ActivateEntity activateEntity = ActivateEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(promoRepository.findById(1L)).thenReturn(Optional.of(promo));
        when(activateRepository.findByUsersEntityAndPromoEntity(users,promo)).thenReturn(activateEntity);
        when(activateRepository.save(activateEntity)).thenReturn(activateEntity);
        ActivateDto activateDto = activateService.ActivatePromo(1L, 1L);
        assertEquals(activateDto.getId(), activateEntity.getId());
    }
}