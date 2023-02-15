package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Entity.ActivateEntity;

public interface ActivateService {
    ActivateDto CreatePromo(Long user_id, Long promo_id);

    ActivateDto ActivatePromo(Long user_id, Long promo_id);

    ActivateDto DeleteActivate(Long activate_id);
}
