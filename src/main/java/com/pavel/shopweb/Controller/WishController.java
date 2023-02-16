package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Dto.WishDto;
import com.pavel.shopweb.Service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wish")
public class WishController {
    private final WishService wishService;

    private static final String WISH_ID = "/{wish_id}";

    private static final String GET_PRODUCT_BY_USER = "/user/{user_id}";

    private static final String ADD_WISH_PRODUCT = "/user/{user_id}/product/{product_id}";

    private static final String CREATE_WISH = "/create/user/{user_id}";

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @RequestMapping(value = ADD_WISH_PRODUCT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WishDto AddWishProduct(@PathVariable Long user_id,
                                  @PathVariable Long product_id,
                                  HttpSession httpSession){
        return wishService.AddProductByWish(user_id, product_id, httpSession);
    }

    @RequestMapping(value = GET_PRODUCT_BY_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> GetProductByUser(@PathVariable Long user_id,
                                             HttpSession httpSession){
        return wishService.GetProductByWish(user_id, httpSession);
    }

    @RequestMapping(value = CREATE_WISH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WishDto CreateWish(@PathVariable Long user_id){
        return wishService.CreateWish(user_id);
    }

    @RequestMapping(value = WISH_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WishDto DeleteWish(@PathVariable Long wish_id){
        return wishService.DeleteWish(wish_id);
    }
}
