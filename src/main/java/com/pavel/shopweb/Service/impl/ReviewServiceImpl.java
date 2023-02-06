package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Entity.ImageEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.ReviewEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.ReviewMapper;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.ReviewRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final UsersRepository usersRepository;

    private final ProductRepository productRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository, UsersRepository usersRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ReviewDto> GetAllReview(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ReviewEntity> reviewEntities = reviewRepository.findAll(pageable);
        if(reviewEntities.isEmpty()){
            throw new NotFoundException("List review is empty!");
        }
        return reviewEntities
                .stream()
                .map(ReviewMapper.INSTANCE::REVIEW_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto GetReviewByProduct(Long product_id) {
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        ReviewEntity reviewEntity = reviewRepository.findByProductEntity(productEntity);
        return ReviewMapper.INSTANCE.REVIEW_DTO(reviewEntity);
    }

    @Override
    @Transactional
    public ReviewDto CreateReview(Long product_id, Long user_id, MultipartFile multipartFile, ReviewEntity reviewEntity) {
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        if(multipartFile != null){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(multipartFile.getOriginalFilename());
            imageEntity.setImage(imageEntity.getImage());
            imageEntity.setSize(multipartFile.getSize());
            imageEntity.setReviewEntities(Arrays.asList(reviewEntity));
            reviewEntity.setImageEntity(imageEntity);
        }
        reviewEntity.setProductEntity(productEntity);
        reviewEntity.setUsersEntity(users);
        ReviewEntity reviewSave = reviewRepository.save(reviewEntity);
        return ReviewMapper.INSTANCE.REVIEW_DTO(reviewEntity);
    }

    @Override
    @Transactional
    public ReviewDto EditReview(Long review_id, MultipartFile multipartFile, ReviewEntity reviewEntity) {
        ReviewEntity review = reviewRepository
                .findById(review_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for review id!");
                });
        if(!multipartFile.isEmpty()){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(multipartFile.getOriginalFilename());
            imageEntity.setImage(imageEntity.getImage());
            imageEntity.setSize(multipartFile.getSize());
            imageEntity.setReviewEntities(Arrays.asList(reviewEntity));
            review.setImageEntity(imageEntity);
        }
        review.setName(reviewEntity.getName());
        review.setDescription(reviewEntity.getDescription());
        review.setMarks(reviewEntity.getMarks());
        ReviewEntity reviewSave = reviewRepository.save(review);
        return ReviewMapper.INSTANCE.REVIEW_DTO(reviewSave);
    }

    @Override
    public ReviewDto DeleteReview(Long review_id) {
        ReviewEntity reviewEntity = reviewRepository
                .findById(review_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for review id!");
                });
        reviewRepository.deleteById(review_id);
        return ReviewMapper.INSTANCE.REVIEW_DTO(reviewEntity);
    }
}
