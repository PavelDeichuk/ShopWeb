package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Entity.ActivateEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ActivateMapperTest {
    @MockBean
    private ActivateMapper activateMapper;

    @Test
    void ACTIVATE_DTO() {
        ActivateEntity activateEntity = ActivateEntity.builder().id(1L).build();
        ActivateDto activateDto = ActivateMapper.INSTANCE.ACTIVATE_DTO(activateEntity);
        assertEquals(activateDto.getId(), activateEntity.getId());
    }
}