package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.UsersDetailRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UsersDetailServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersDetailRepository usersDetailRepository;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UsersDetailServiceImpl usersDetailServiceimpl;


    @Test
    public void getUserDetailById() {
        UsersDetailEntity usersDetailEntity = new UsersDetailEntity();
        UsersEntity users = UsersEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(usersDetailRepository.findByUsersEntity(users)).thenReturn(usersDetailEntity);
        UsersDetailDto usersDetailDto = usersDetailServiceimpl.GetUserDetailById(1L);
        verify(usersRepository).findById(1L);
        verify(usersDetailRepository).findByUsersEntity(users);
    }

    @Test
    void editUserDetailById() {
        UsersDetailEntity usersDetailEntity = new UsersDetailEntity();
        UsersEntity users = new UsersEntity();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(usersDetailRepository.findByUsersEntity(users)).thenReturn(usersDetailEntity);
        UsersDetailDto usersDetailDto = usersDetailServiceimpl.EditUserDetailById(1L, usersDetailEntity, bindingResult);
        verify(usersRepository).findById(1L);
        verify(usersDetailRepository).findByUsersEntity(users);
    }
}