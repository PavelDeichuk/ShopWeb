package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Entity.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDto QUESTION_DTO(QuestionEntity questionEntity);
}
