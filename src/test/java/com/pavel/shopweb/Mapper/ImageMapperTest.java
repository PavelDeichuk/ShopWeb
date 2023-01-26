package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ImageDto;
import com.pavel.shopweb.Entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ImageMapperTest {
    @MockBean
    private ImageMapper imageMapper;

    @Test
    void IMAGE_DTO() {
        ImageEntity image = ImageEntity.builder().id(1L).build();
        ImageDto imageDto = imageMapper.INSTANCE.IMAGE_DTO(image);
        assertEquals(imageDto.getId(), image.getId());
    }
}