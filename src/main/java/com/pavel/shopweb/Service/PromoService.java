package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;

public interface PromoService {

    PromoDto GetPromoByName(String promo_name);
    PromoDto CreatePromo(PromoEntity promoEntity);

    PromoDto EditPromo(Long promo_id, PromoEntity promoEntity);

    PromoDto DeletePromo(Long promo_id);
}
