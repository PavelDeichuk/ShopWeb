package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Service.PromoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoController {
    private final PromoService promoService;

    private static final String PROMO_NAME = "/{promo_name}";

    private static final String PROMO_ID = "/{promo_id}";

    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }

    @RequestMapping(value = PROMO_NAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PromoDto GetPromoByName(@PathVariable String promo_name){
        return promoService.GetPromoByName(promo_name);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PromoDto CreatePromo(@RequestBody PromoEntity promoEntity){
        return promoService.CreatePromo(promoEntity);
    }

    @RequestMapping(value = PROMO_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public PromoDto EditPromo(@PathVariable Long promo_id,
                              @RequestBody PromoEntity promoEntity){
        return promoService.EditPromo(promo_id, promoEntity);
    }

    @RequestMapping(value = PROMO_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PromoDto DeletePromo(@PathVariable Long promo_id){
        return promoService.DeletePromo(promo_id);
    }
}
