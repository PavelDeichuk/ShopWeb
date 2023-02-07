package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.DiscountDto;
import com.pavel.shopweb.Entity.DiscountEntity;

import java.util.List;

public interface DiscountService {
    List<DiscountDto> GetAllDiscount(int page, int size);

    DiscountDto CreateDiscount(Long product_id,DiscountEntity discountEntity);

    DiscountDto EditDiscount(Long product_id, Long discount_id, DiscountEntity discountEntity);

    DiscountDto DeleteDiscount(Long discount_id);
}
