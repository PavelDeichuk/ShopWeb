package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Dto.ProductDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface BucketService {
    BucketDto AddProductToBucket(Long user_id, Long product_id, HttpSession httpSession);

    List<ProductDto> GetProductByBucket(Long user_id, HttpSession httpSession);

    BucketDto CreateBucketUser(Long user_id);

    BucketDto DeleteBucket(Long bucket_id);
}
