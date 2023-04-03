package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Entity.QuestionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class QuestionMapperTest {

    @MockBean
    private QuestionMapper questionMapper;

    @Test
    void QUESTION_DTO() {
        QuestionEntity question = QuestionEntity.builder().id(1L).build();
        QuestionDto questionDto = questionMapper.INSTANCE.QUESTION_DTO(question);
        assertEquals(questionDto.getId(), question.getId());
    }
}