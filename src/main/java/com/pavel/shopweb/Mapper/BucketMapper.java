package com.pavel.shopweb.Mapper;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Entity.BucketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BucketMapper {
    BucketMapper INSTANCE = Mappers.getMapper(BucketMapper.class);

    BucketDto BUCKET_DTO(BucketEntity bucketEntity);
}
