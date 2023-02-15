package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Entity.ActivateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivateMapper {
    ActivateMapper INSTANCE = Mappers.getMapper(ActivateMapper.class);

    ActivateDto ACTIVATE_DTO(ActivateEntity activateEntity);
}