package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ReviewMapperTest {

    @MockBean
    private ReviewMapper reviewMapper;

    @Test
    void REVIEW_DTO() {
        ReviewEntity reviewEntity = ReviewEntity.builder().id(1L).build();
        ReviewDto reviewDto = reviewMapper.INSTANCE.REVIEW_DTO(reviewEntity);
        assertEquals(reviewDto.getId(), reviewEntity.getId());
    }
}