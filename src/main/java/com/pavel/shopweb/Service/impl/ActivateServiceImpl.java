package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ActivateDto;
import com.pavel.shopweb.Entity.ActivateEntity;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.ActivateMapper;
import com.pavel.shopweb.Repository.ActivateRepository;
import com.pavel.shopweb.Repository.PromoRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.ActivateService;
import org.springframework.stereotype.Service;

@Service
public class ActivateServiceImpl implements ActivateService {
    private final ActivateRepository activateRepository;

    private final UsersRepository usersRepository;

    private final PromoRepository promoRepository;

    public ActivateServiceImpl(ActivateRepository activateRepository, UsersRepository usersRepository, PromoRepository promoRepository) {
        this.activateRepository = activateRepository;
        this.usersRepository = usersRepository;
        this.promoRepository = promoRepository;
    }

    @Override
    public ActivateDto CreatePromo(Long user_id, Long promo_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        PromoEntity promo = promoRepository
                .findById(promo_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for promo_id!");
                });
        ActivateEntity activateEntity = new ActivateEntity();
        activateEntity.setUsersEntity(users);
        activateEntity.setPromoEntity(promo);
        activateEntity.setActivates(0L);
        activateRepository.save(activateEntity);
        return ActivateMapper.INSTANCE.ACTIVATE_DTO(activateEntity);
    }

    @Override
    public ActivateDto ActivatePromo(Long user_id, Long promo_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        PromoEntity promo = promoRepository
                .findById(promo_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for promo_id!");
                });
        ActivateEntity activate = activateRepository.findByUsersEntityAndPromoEntity(users, promo);
        activate.setActivates(activate.getActivates() + 1);
        activateRepository.save(activate);
        return ActivateMapper.INSTANCE.ACTIVATE_DTO(activate);
    }

    @Override
    public ActivateDto DeleteActivate(Long activate_id) {
        ActivateEntity activateEntity = activateRepository
                .findById(activate_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for activate id!");
                });
        activateRepository.deleteById(activate_id);
        return ActivateMapper.INSTANCE.ACTIVATE_DTO(activateEntity);
    }
}
