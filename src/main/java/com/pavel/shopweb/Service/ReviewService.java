package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Entity.ReviewEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> GetAllReview(int page, int size);

    ReviewDto GetReviewByProduct(Long product_id);

    ReviewDto CreateReview(Long product_id, Long user_id, MultipartFile multipartFile, ReviewEntity reviewEntity);

    ReviewDto EditReview(Long review_id, MultipartFile multipartFile, ReviewEntity reviewEntity);

    ReviewDto DeleteReview(Long review_id);
}
