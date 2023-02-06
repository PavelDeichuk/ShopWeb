package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDto REVIEW_DTO(ReviewEntity reviewEntity);
}
