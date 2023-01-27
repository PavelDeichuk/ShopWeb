package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductDto> GetAllProduct(int page, int size);

    ProductDto GetProductById(Long product_id);

    ProductDto CreateProduct(ProductEntity productEntity);

    ProductDto CreateImageProduct(Long product_id, MultipartFile multipartFile) throws IOException;

    ProductDto EditImageProduct(Long product_id, MultipartFile multipartFile) throws IOException;

    ProductDto EditProduct(Long product_id, ProductEntity productEntity);

    ProductDto DeleteProduct(Long product_id);
}
