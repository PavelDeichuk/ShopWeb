package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Service.ActivateService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activate")
public class ActivateController {
    private final ActivateService activateService;

    private static final String ACTIVATE_ID = "/{activate_id}";

    private static final String CREATE_PROMO = "/create/user/{user_id}/promo/{promo_id}";

    private static final String ActivatePromo = "/user/{user_id}/promo/{promo_id}";

    public ActivateController(ActivateService activateService) {
        this.activateService = activateService;
    }

    @RequestMapping(value = CREATE_PROMO, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivateDto CreatePromo(@PathVariable Long user_id, @PathVariable Long promo_id){
        return activateService.CreatePromo(user_id, promo_id);
    }

    @RequestMapping(value = ActivatePromo, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivateDto ActivatePromo(@PathVariable Long promo_id, @PathVariable Long user_id){
        return activateService.ActivatePromo(promo_id, user_id);
    }

    @RequestMapping(value = ACTIVATE_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivateDto DeleteActivate(@PathVariable Long activate_id){
        return activateService.DeleteActivate(activate_id);
    }

}
