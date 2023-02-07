package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.DiscountDto;
import com.pavel.shopweb.Entity.DiscountEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.DiscountMapper;
import com.pavel.shopweb.Repository.DiscountRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Service.DiscountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    private final ProductRepository productRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<DiscountDto> GetAllDiscount(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<DiscountEntity> discountEntities = discountRepository.findAll(pageable);
        if(discountEntities.isEmpty()){
            throw new BadRequestException("discount list is empty!");
        }
        return discountEntities
                .stream()
                .map(DiscountMapper.INSTANCE::DISCOUNT_DTO)
                .collect(Collectors.toList());
    }


    @Override
    public DiscountDto CreateDiscount(Long product_id, DiscountEntity discountEntity) {
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product id!");
                });
        if(productEntity.getPrice() < discountEntity.getPrice()){
            throw new BadRequestException("Discount price can't be less product price");
        }
        productEntity.setDiscountEntity(discountEntity);
        discountEntity.setProductEntities(Arrays.asList(productEntity));
        DiscountEntity discountSave = discountRepository.save(discountEntity);
        return DiscountMapper.INSTANCE.DISCOUNT_DTO(discountSave);
    }

    @Override
    public DiscountDto EditDiscount(Long product_id, Long discount_id, DiscountEntity discountEntity) {
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product id!");
                });
        DiscountEntity discount = discountRepository
                .findById(discount_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for discount_id");
                });
        if(productEntity.getPrice() < discountEntity.getPrice()){
            throw new BadRequestException("Discount price can't be less product price");
        }
        discount.setProductEntities(Arrays.asList(productEntity));
        discount.setPrice(discountEntity.getPrice());
        discount.setStartAt(discount.getStartAt());
        discount.setEndAt(discount.getEndAt());
        DiscountEntity discountSave = discountRepository.save(discountEntity);
        return DiscountMapper.INSTANCE.DISCOUNT_DTO(discountSave);
    }

    @Override
    public DiscountDto DeleteDiscount(Long discount_id) {
        DiscountEntity discount = discountRepository
                .findById(discount_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for discount id!");
                });
        discountRepository.deleteById(discount_id);
        return DiscountMapper.INSTANCE.DISCOUNT_DTO(discount);
    }
}
