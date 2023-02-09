package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.CreditCardRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class CreditCardServiceImplTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private CryptoService cryptoService;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Test
    void getCreditCardByUser() {
        UsersEntity users = UsersEntity.builder().id(1L).build();
        CreditCardEntity creditCard = CreditCardEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(creditCardRepository.findByUsersEntity(users)).thenReturn(Arrays.asList(creditCard));
        List<CreditCardDto> creditCardDto = creditCardService.GetCreditCardByUser(1L);
        assertEquals(creditCardDto.get(0).getId(), creditCard.getId());
    }

    @Test
    void createCreditCard() throws Exception{
        UsersEntity users = UsersEntity.builder().id(1L).build();
        CreditCardEntity creditCard = CreditCardEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(creditCardRepository.save(creditCard)).thenReturn(creditCard);
        when(cryptoService.encrypt("test")).thenReturn("test");
        CreditCardDto creditCardDto = creditCardService.CreateCreditCard(1L, creditCard);
        assertEquals(creditCardDto.getId(), creditCard.getId());
    }

    @Test
    void editCreditCard() throws Exception{
        CreditCardEntity creditCard = CreditCardEntity.builder().id(1L).build();
        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));
        when(creditCardRepository.save(creditCard)).thenReturn(creditCard);
        when(cryptoService.encrypt("test")).thenReturn("test");
        CreditCardDto creditCardDto = creditCardService.EditCreditCard(1L, creditCard);
        assertEquals(creditCardDto.getId(), creditCard.getId());
    }

}