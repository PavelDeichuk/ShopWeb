package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.CategoryDto;
import com.pavel.shopweb.Entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> GetAllCategories(int page, int size);

    CategoryDto CreateCategory(CategoryEntity categoryEntity);

    boolean AddProductToCategory(Long product_id, Long category_id);

    CategoryDto EditCategory(Long category_id, CategoryEntity categoryEntity);

    CategoryDto DeleteCategory(Long category_id);
}
