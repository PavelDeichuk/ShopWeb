package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.BucketDto;
import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Service.BucketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bucket")
public class BucketController {

    private final BucketService bucketService;

    private static final String BUCKET_ID = "/{bucket_id}";

    private static final String GET_PRODUCT_BY_USER = "/user/{user_id}";

    private static final String CREATE_BUCKET = "/create/user/{user_id}";

    private static final String ADD_BUCKET_PRODUCT = "/user/{user_id}/product/{product_id}";

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @RequestMapping(value = GET_PRODUCT_BY_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> GetProductByBucket(@PathVariable Long user_id,
                                               HttpSession httpSession){
        return bucketService.GetProductByBucket(user_id, httpSession);
    }

    @RequestMapping(value = ADD_BUCKET_PRODUCT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BucketDto AddProductByBucket(@PathVariable Long user_id,
                                        @PathVariable Long product_id,
                                        HttpSession httpSession){
        return bucketService.AddProductToBucket(user_id, product_id, httpSession);
    }

    @RequestMapping(value = CREATE_BUCKET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BucketDto CreateBucketUser(@PathVariable Long user_id){
        return bucketService.CreateBucketUser(user_id);
    }

    @RequestMapping(value = BUCKET_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BucketDto DeleteBucket(@PathVariable Long bucket_id){
        return bucketService.DeleteBucket(bucket_id);
    }
}
