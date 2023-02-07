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

    private static final String PRODUCT_ID = "/product/{product_id}";

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
}
