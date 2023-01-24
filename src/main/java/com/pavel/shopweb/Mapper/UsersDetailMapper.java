package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersDetailMapper {
    UsersDetailMapper INSTANCE = Mappers.getMapper(UsersDetailMapper.class);

    UsersDetailDto USERS_DETAIL_DTO(UsersDetailEntity usersDetailEntity);
}
