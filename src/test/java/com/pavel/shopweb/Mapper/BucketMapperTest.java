package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Entity.BucketEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class BucketMapperTest {

    @MockBean
    private BucketMapper bucketMapper;

    @Test
    void BUCKET_DTO() {
        BucketEntity bucketEntity = BucketEntity.builder().id(1L).build();
        BucketDto bucketDto = bucketMapper.INSTANCE.BUCKET_DTO(bucketEntity);
        assertEquals(bucketDto.getId(), bucketEntity.getId());
    }
}