package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Dto.WishDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface WishService {
    WishDto AddProductByWish(Long user_id, Long product_id, HttpSession httpSession);

    List<ProductDto> GetProductByWish(Long user_id, HttpSession httpSession);

    WishDto CreateWish(Long user_id);

    WishDto DeleteWish(Long wish_id);
}
