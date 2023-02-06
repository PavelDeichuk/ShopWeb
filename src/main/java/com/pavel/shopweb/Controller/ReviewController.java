package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Entity.ReviewEntity;
import com.pavel.shopweb.Service.ReviewService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private static final String CREATE_REVIEW = "/product/{product_id}/user/{user_id}";

    private static final String GET_REVIEW_PRODUCT_ID = "/product/{product_id}";

    private static final String REVIEW_ID = "/{review_id}";
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewDto> GetAllReview(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size){
        return reviewService.GetAllReview(page, size);
    }

    @RequestMapping(value = GET_REVIEW_PRODUCT_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDto GetReviewById(@PathVariable Long product_id){
        return reviewService.GetReviewByProduct(product_id);
    }

    @RequestMapping(value = CREATE_REVIEW,method = RequestMethod.POST)
    public ReviewDto CreateReview(@PathVariable Long product_id,
                                  @PathVariable Long user_id,
                                  @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                  @RequestPart(value = "review") ReviewEntity reviewEntity){
        return reviewService.CreateReview(product_id, user_id, multipartFile, reviewEntity);
    }

    @RequestMapping(value = REVIEW_ID, method = RequestMethod.PUT)
    public ReviewDto EditReview(@PathVariable Long review_id,
                                @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                @RequestPart(value = "review") ReviewEntity reviewEntity){
        return reviewService.EditReview(review_id, multipartFile, reviewEntity);
    }

    @RequestMapping(value = REVIEW_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDto DeleteReview(@PathVariable Long review_id){
        return reviewService.DeleteReview(review_id);
    }
}
