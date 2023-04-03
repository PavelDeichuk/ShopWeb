package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.CategoryDto;
import com.pavel.shopweb.Entity.CategoryEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.CategoryMapper;
import com.pavel.shopweb.Repository.CategoryRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryDto> GetAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        if(categoryEntities.isEmpty()){
            throw new NotFoundException("List category is empty!");
        }
        return categoryEntities
                .stream()
                .map(CategoryMapper.INSTANCE::CATEGORY_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto CreateCategory(CategoryEntity categoryEntity) {
        categoryRepository
                .findByName(categoryEntity.getName())
                .ifPresent(name -> {
                    throw new BadRequestException("name is exist for category!");
                });
        CategoryEntity categorysave = categoryRepository.save(categoryEntity);
        return CategoryMapper.INSTANCE.CATEGORY_DTO(categorysave);
    }

    @Override
    public boolean AddProductToCategory(Long product_id, Long category_id) {
        ProductEntity product = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        CategoryEntity category = categoryRepository
                .findById(category_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for category_id");
                });
        List<ProductEntity> categoryProductEntities = category.getProductEntities();
        categoryProductEntities.add(product);
        category.setProductEntities(categoryProductEntities);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public CategoryDto EditCategory(Long category_id, CategoryEntity categoryEntity) {
        CategoryEntity categoryId = categoryRepository
                .findById(category_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for category_id");
                });
        categoryId.setName(categoryId.getName());
        categoryId.setProductEntities(categoryEntity.getProductEntities());
        categoryRepository.save(categoryId);
        return CategoryMapper.INSTANCE.CATEGORY_DTO(categoryId);
    }

    @Override
    public CategoryDto DeleteCategory(Long category_id) {
        CategoryEntity categoryEntity = categoryRepository
                .findById(category_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for category id!");
                });
        categoryRepository.deleteById(category_id);
        return CategoryMapper.INSTANCE.CATEGORY_DTO(categoryEntity);
    }
}
