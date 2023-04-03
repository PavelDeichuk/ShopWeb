package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.DiscountDto;
import com.pavel.shopweb.Entity.DiscountEntity;
import com.pavel.shopweb.Service.DiscountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountService discountService;

    private static final String DISCOUNT_ID = "/{discount_id}";

    private static final String PRODUCT_ID = "/product/{product_id}";

    private static final String EDIT_DISCOUNT = "/{discount_id}/product/{product_id}";

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DiscountDto> GetAllDiscount(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size){
        return discountService.GetAllDiscount(page, size);
    }

    @RequestMapping(value = PRODUCT_ID,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public DiscountDto CreateDiscount(@PathVariable Long product_id,
                                      @RequestBody DiscountEntity discountEntity){
        return discountService.CreateDiscount(product_id, discountEntity);
    }

    @RequestMapping(value = EDIT_DISCOUNT, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public DiscountDto EditDiscount(@PathVariable Long product_id,
                                    @PathVariable Long discount_id ,
                                    @RequestBody DiscountEntity discountEntity){
        return discountService.EditDiscount(product_id, discount_id, discountEntity);
    }

    @RequestMapping(value = DISCOUNT_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DiscountDto DeleteDiscount(@PathVariable Long discount_id){
        return discountService.DeleteDiscount(discount_id);
    }
}
