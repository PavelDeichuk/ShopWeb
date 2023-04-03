package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.CreditCardDto;
import com.pavel.shopweb.Entity.CreditCardEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.CreditCardMapper;
import com.pavel.shopweb.Repository.CreditCardRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.CreditCardService;
import com.pavel.shopweb.Service.CryptoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final UsersRepository usersRepository;

    private final CreditCardRepository creditCardRepository;

    private final CryptoService cryptoService;

    public CreditCardServiceImpl(UsersRepository usersRepository, CreditCardRepository creditCardRepository, CryptoService cryptoService) {
        this.usersRepository = usersRepository;
        this.creditCardRepository = creditCardRepository;
        this.cryptoService = cryptoService;
    }

    @Override
    public List<CreditCardDto> GetCreditCardByUser(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        List<CreditCardEntity> creditCard = creditCardRepository.findByUsersEntity(users);
        return creditCard
                .stream()
                .map(CreditCardMapper.INSTANCE::CREDIT_CARD_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditCardDto CreateCreditCard(Long user_id, CreditCardEntity creditCardEntity) throws Exception {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        creditCardEntity.setFullName(cryptoService.encrypt(creditCardEntity.getFullName()));
        creditCardEntity.setNumber(cryptoService.encrypt(creditCardEntity.getNumber()));
        creditCardEntity.setCvv(cryptoService.encrypt(creditCardEntity.getCvv()));
        creditCardEntity.setUsersEntity(users);
        CreditCardEntity creditSave = creditCardRepository.save(creditCardEntity);
        return CreditCardMapper.INSTANCE.CREDIT_CARD_DTO(creditSave);
    }

    @Override
    public CreditCardDto EditCreditCard(Long credit_id, CreditCardEntity creditCardEntity) throws Exception {
        CreditCardEntity creditCard = creditCardRepository
                .findById(credit_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for credit id!");
                });
        creditCard.setFullName(cryptoService.encrypt(creditCardEntity.getFullName()));
        creditCard.setNumber(cryptoService.encrypt(creditCardEntity.getNumber()));
        creditCard.setValidity(creditCardEntity.getValidity());
        creditCard.setCvv(cryptoService.encrypt(creditCardEntity.getCvv()));
        CreditCardEntity creditSave = creditCardRepository.save(creditCard);
        return CreditCardMapper.INSTANCE.CREDIT_CARD_DTO(creditSave);
    }

    @Override
    public CreditCardDto DeleteCreditCard(Long credit_card) {
        CreditCardEntity creditCard = creditCardRepository
                .findById(credit_card)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for credit_card");
                });
        creditCardRepository.deleteById(credit_card);
        return CreditCardMapper.INSTANCE.CREDIT_CARD_DTO(creditCard);
    }
}
