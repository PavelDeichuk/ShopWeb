package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class UsersDetailMapperTest {

    @MockBean
    private UsersDetailMapper usersDetailMapper;

    @Test
    void USERS_DETAIL_DTO() {
        UsersDetailEntity usersDetailEntity = UsersDetailEntity.builder().id(1L).build();
        UsersDetailDto usersDetailDto = usersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetailEntity);
        assertEquals(usersDetailDto.getId(), usersDetailEntity.getId());
    }
}