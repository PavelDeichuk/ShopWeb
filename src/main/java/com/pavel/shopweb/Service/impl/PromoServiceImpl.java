package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.PromoDto;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.PromoMapper;
import com.pavel.shopweb.Repository.PromoRepository;
import com.pavel.shopweb.Service.PromoService;
import org.springframework.stereotype.Service;

@Service
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepository;

    public PromoServiceImpl(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    @Override
    public PromoDto GetPromoByName(String promo_name) {
        PromoEntity promo = promoRepository
                .findByName(promo_name)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for promo_name");
                });
        return PromoMapper.INSTANCE.PROMO_DTO(promo);
    }

    @Override
    public PromoDto CreatePromo(PromoEntity promoEntity) {
        promoRepository
                .findByName(promoEntity.getName())
                .ifPresent(promo -> {
                    throw new BadRequestException("Promo name is exist!");
                });
        PromoEntity promoSave = promoRepository.save(promoEntity);
        return PromoMapper.INSTANCE.PROMO_DTO(promoSave);
    }

    @Override
    public PromoDto EditPromo(Long promo_id, PromoEntity promoEntity) {
        PromoEntity promo = promoRepository
                .findById(promo_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for promo id!");
                });
        promoRepository
                .findByName(promoEntity.getName())
                .ifPresent(name -> {
                    throw new BadRequestException("promo name is exist!");
                });
        promo.setName(promoEntity.getName());
        promo.setPrice(promoEntity.getPrice());
        promo.setStartAt(promoEntity.getStartAt());
        promo.setEndAt(promoEntity.getEndAt());
        promoRepository.save(promo);
        return PromoMapper.INSTANCE.PROMO_DTO(promo);
    }

    @Override
    public PromoDto DeletePromo(Long promo_id) {
        PromoEntity promo = promoRepository
                .findById(promo_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for promo_id!");
                });
        promoRepository.deleteById(promo_id);
        return PromoMapper.INSTANCE.PROMO_DTO(promo);
    }
}
