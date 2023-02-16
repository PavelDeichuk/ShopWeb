package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Dto.WishDto;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Entity.WishEntity;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.ProductMapper;
import com.pavel.shopweb.Mapper.WishMapper;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Repository.WishRepository;
import com.pavel.shopweb.Service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    private final UsersRepository usersRepository;

    private final ProductRepository productRepository;


    public WishServiceImpl(WishRepository wishRepository, UsersRepository usersRepository, ProductRepository productRepository) {
        this.wishRepository = wishRepository;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
    }


    @Override
    public WishDto AddProductByWish(Long user_id, Long product_id, HttpSession httpSession) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id!");
                });
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        WishEntity wishEntity = wishRepository.findByUsersEntity(users);
        List<ProductEntity> attribute = (List<ProductEntity>) httpSession.getAttribute(wishEntity.getSession());
        if(attribute == null){
            attribute = new ArrayList<>();
        }
        attribute.add(productEntity);
        httpSession.setAttribute(wishEntity.getSession(), attribute);
        wishRepository.save(wishEntity);
        return WishMapper.INSTANCE.WISH_DTO(wishEntity);
    }

    @Override
    public List<ProductDto> GetProductByWish(Long user_id, HttpSession httpSession) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        WishEntity wishEntity = wishRepository.findByUsersEntity(users);
        List<ProductEntity> productDtos = (List<ProductEntity>) httpSession.getAttribute(wishEntity.getSession());
        return productDtos
                .stream()
                .map(ProductMapper.INSTANCE::PRODUCT_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public WishDto CreateWish(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        WishEntity wishEntity = new WishEntity();
        wishEntity.setUsersEntity(users);
        wishEntity.setSession(UUID.randomUUID().toString());
        wishRepository.save(wishEntity);
        return WishMapper.INSTANCE.WISH_DTO(wishEntity);
    }

    @Override
    public WishDto DeleteWish(Long wish_id) {
        WishEntity wishEntity = wishRepository
                .findById(wish_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for wish id!");
                });
        wishRepository.deleteById(wish_id);
        return WishMapper.INSTANCE.WISH_DTO(wishEntity);
    }
}
