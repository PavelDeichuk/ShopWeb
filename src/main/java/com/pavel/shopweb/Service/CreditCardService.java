package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;

import java.util.List;

public interface CreditCardService {

    List<CreditCardDto> GetCreditCardByUser(Long user_id);
    CreditCardDto CreateCreditCard(Long user_id, CreditCardEntity creditCardEntity) throws Exception;

    CreditCardDto EditCreditCard(Long credit_id, CreditCardEntity creditCardEntity) throws Exception;

    CreditCardDto DeleteCreditCard(Long credit_card);
}
