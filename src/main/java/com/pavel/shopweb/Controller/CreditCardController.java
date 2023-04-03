package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;
import com.pavel.shopweb.Service.CreditCardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit")
public class CreditCardController {
    private final CreditCardService creditCardService;

    private static final String USER_ID = "/user/{user_id}";

    private static final String CREDIT_CARD = "/{credit_id}";

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<CreditCardDto> GetCreditByUser(@PathVariable Long user_id){
        return creditCardService.GetCreditCardByUser(user_id);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardDto CreateCreditCard(@PathVariable Long user_id,
                                          @RequestBody CreditCardEntity creditCardEntity) throws Exception {
        return creditCardService.CreateCreditCard(user_id,creditCardEntity);
    }

    @RequestMapping(value = CREDIT_CARD, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardDto EditCreditCard(@PathVariable Long credit_id,
                                        @RequestBody CreditCardEntity creditCardEntity) throws Exception {
        return creditCardService.EditCreditCard(credit_id, creditCardEntity);
    }

    @RequestMapping(value = CREDIT_CARD, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardDto DeleteCreditCard(@PathVariable Long credit_id){
        return creditCardService.DeleteCreditCard(credit_id);
    }
}
