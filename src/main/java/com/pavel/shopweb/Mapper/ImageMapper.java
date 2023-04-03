package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ImageDto;
import com.pavel.shopweb.Entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto IMAGE_DTO(ImageEntity imageEntity);
}
