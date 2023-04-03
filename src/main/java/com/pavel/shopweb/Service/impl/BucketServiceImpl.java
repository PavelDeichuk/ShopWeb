package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.BucketEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.BucketMapper;
import com.pavel.shopweb.Mapper.ProductMapper;
import com.pavel.shopweb.Repository.BucketRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.BucketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {

    private final UsersRepository usersRepository;

    private final BucketRepository bucketRepository;

    private final ProductRepository productRepository;

    public BucketServiceImpl(UsersRepository usersRepository, BucketRepository bucketRepository, ProductRepository productRepository) {
        this.usersRepository = usersRepository;
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
    }

    @Override
    public BucketDto AddProductToBucket(Long user_id, Long product_id, HttpSession httpSession) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        BucketEntity bucketEntity = bucketRepository.findByUsersEntity(users);
        if(bucketEntity.getSession() == null){
            throw new BadRequestException("session is empty, please create bucket!");
        }
        List<ProductEntity> attribute = (List<ProductEntity>) httpSession.getAttribute(bucketEntity.getSession());
        if(attribute == null){
            attribute = new ArrayList<>();
        }
        attribute.add(productEntity);
        httpSession.setAttribute(bucketEntity.getSession(), attribute);
        bucketRepository.save(bucketEntity);
        return BucketMapper.INSTANCE.BUCKET_DTO(bucketEntity);
    }

    @Override
    public List<ProductDto> GetProductByBucket(Long user_id, HttpSession httpSession) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        BucketEntity bucketEntity = bucketRepository.findByUsersEntity(users);
        if(bucketEntity.getSession() == null){
            throw new BadRequestException("session is empty, please create bucket!");
        }
        List<ProductEntity> productEntities = (List<ProductEntity>) httpSession.getAttribute(bucketEntity.getSession());
        if(productEntities.isEmpty()){
            throw new BadRequestException("product is empty!");
        }
        return productEntities
                .stream()
                .map(ProductMapper.INSTANCE::PRODUCT_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public BucketDto CreateBucketUser(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        BucketEntity bucketEntity = new BucketEntity();
        bucketEntity.setUsersEntity(users);
        bucketEntity.setSession(UUID.randomUUID().toString());
        bucketRepository.save(bucketEntity);
        return BucketMapper.INSTANCE.BUCKET_DTO(bucketEntity);
    }

    @Override
    public BucketDto DeleteBucket(Long bucket_id) {
        BucketEntity bucketEntity = bucketRepository
                .findById(bucket_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for bucket_id");
                });
        bucketRepository.deleteById(bucket_id);
        return BucketMapper.INSTANCE.BUCKET_DTO(bucketEntity);
    }
}
