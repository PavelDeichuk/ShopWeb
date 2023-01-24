package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class UsersMapperTest {

    @MockBean
    private UsersMapper usersMapper;

    @Test
    void USERS_DTO() {
        UsersEntity users = UsersEntity.builder().id(1L).build();
        UsersDto usersDto = usersMapper.INSTANCE.USERS_DTO(users);
        assertEquals(usersDto.getId(), users.getId());
    }
}