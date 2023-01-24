package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Mapper.UsersMapper;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UsersDto> GetAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UsersEntity> usersEntities = usersRepository.findAll(pageable);
        if(usersEntities.isEmpty()){
            throw new RuntimeException("List users is empty!");
        }
        return usersEntities
                .stream()
                .map(UsersMapper.INSTANCE::USERS_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto GetUserById(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user id!");
                });
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    public UsersDto CreateUser(UsersEntity usersEntity, BindingResult bindingResult) {
        usersRepository
                .findByUsername(usersEntity.getUsername())
                .ifPresent(users -> {
                    throw new RuntimeException("Username is exist!");
                });
        usersRepository
                .findByEmail(usersEntity.getEmail())
                .ifPresent(email -> {
                    throw new RuntimeException("Email is exist!");
                });
        if(!Objects.equals(usersEntity.getPassword(), usersEntity.getPassword2())){
            throw new RuntimeException("password is not equals!");
        }
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (var errors : fieldErrors){
                throw new RuntimeException(errors.getObjectName() + " " + errors.getDefaultMessage());
            }
        }
        UsersEntity users = usersRepository
                .save(
                        UsersEntity
                                .builder()
                                .username(usersEntity.getUsername())
                                .password(usersEntity.getPassword())
                                .email(usersEntity.getEmail())
                                .mfa(usersEntity.getMfa())
                                .role("QUEST")
                                .build()
                );
        return UsersMapper.INSTANCE.USERS_DTO(usersEntity);
    }

    @Override
    public UsersDto ActivateUser(String activationCode) {
        UsersEntity users = usersRepository
                .findByActivation(activationCode)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for activationCode");
                });
        users.setRole("USER");
        users.setActivationToken(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    public UsersDto ResetPassword(String email) {
        UsersEntity users = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for email");
                });
        users.setResetToken(UUID.randomUUID().toString());
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }


    @Override
    public UsersDto ChangePassword(String passToken, UsersEntity usersEntity) {
        UsersEntity users = usersRepository
                .findByResetToken(passToken)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for passToken");
                });
        if(!Objects.equals(usersEntity.getPassword(), usersEntity.getPassword2())){
            throw new RuntimeException("password is not equals!");
        }
        users.setPassword(usersEntity.getPassword());
        users.setResetToken(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    public UsersDto EditUser(Long user_id, UsersEntity usersEntity) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user id!");
                });
        users.setUsername(usersEntity.getUsername());
        users.setMfa(usersEntity.getMfa());
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    public UsersDto DeleteUser(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for username");
                });
        usersRepository.deleteById(user_id);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }
}
