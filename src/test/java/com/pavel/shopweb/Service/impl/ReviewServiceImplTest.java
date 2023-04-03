package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.ReviewEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.ReviewRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void getAllReview() {
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        reviewEntities.add(null);
        PageImpl<ReviewEntity> pageImpl = new PageImpl<>(reviewEntities);
        when(reviewRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, reviewService.GetAllReview(1, 3).size());
        verify(reviewRepository).findAll((Pageable) any());
    }

    @Test
    void getReviewByProduct() {
        ProductEntity productEntity = new ProductEntity();
        ReviewEntity reviewEntity = ReviewEntity.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(reviewRepository.findByProductEntity(productEntity)).thenReturn(reviewEntity);
        ReviewDto reviewDto = reviewService.GetReviewByProduct(1L);
        assertEquals(reviewDto.getId(), reviewEntity.getId());
    }

    @Test
    void createReview() {
        ProductEntity productEntity = new ProductEntity();
        UsersEntity users = new UsersEntity();
        ReviewEntity reviewEntity = ReviewEntity.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(reviewRepository.save(reviewEntity)).thenReturn(reviewEntity);
        ReviewDto reviewDto = reviewService.CreateReview(1L, 1L, multipartFile, reviewEntity);
        assertEquals(reviewDto.getId(), reviewEntity.getId());
    }

    @Test
    void editReview() {
        ReviewEntity reviewEntity = ReviewEntity.builder().id(1L).build();
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(reviewEntity));
        when(reviewRepository.save(reviewEntity)).thenReturn(reviewEntity);
        ReviewDto reviewDto = reviewService.EditReview(1L, multipartFile, reviewEntity);
        assertEquals(reviewDto.getId(), reviewEntity.getId());
    }

}