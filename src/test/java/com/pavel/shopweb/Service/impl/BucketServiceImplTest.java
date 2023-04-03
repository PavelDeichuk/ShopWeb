package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.BucketEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.BucketRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class BucketServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private BucketRepository bucketRepository;

    @Mock
    private ProductRepository productRepository;

    @MockBean
    private HttpSession httpSession;

    @InjectMocks
    private BucketServiceImpl bucketService;

    @Test
    void addProductToBucket() {
        UsersEntity users = new UsersEntity();
        ProductEntity productEntity = new ProductEntity();
        BucketEntity bucketEntity = BucketEntity.builder().id(1L).session(UUID.randomUUID().toString()).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(bucketRepository.findByUsersEntity(users)).thenReturn(bucketEntity);
        when(bucketRepository.save(bucketEntity)).thenReturn(bucketEntity);
        BucketDto bucketDto = bucketService.AddProductToBucket(1L,1L, httpSession);
        assertEquals(bucketDto.getId(), bucketEntity.getId());
    }

    @Test
    void getProductByBucket() {
        UsersEntity users = new UsersEntity();
        BucketEntity bucketEntity = BucketEntity.builder().id(1L).session("test").build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(bucketRepository.findByUsersEntity(users)).thenReturn(bucketEntity);
        List<ProductDto> productDtos = bucketService.GetProductByBucket(1L, httpSession);
        verify(bucketService).GetProductByBucket(1L, httpSession);
    }
}