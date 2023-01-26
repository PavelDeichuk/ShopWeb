package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.ImageEntity;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.UsersMapper;
import com.pavel.shopweb.Repository.ImageRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.TotpService;
import com.pavel.shopweb.Service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final TotpService totpService;

    public UsersServiceImpl(UsersRepository usersRepository, TotpService totpService) {
        this.usersRepository = usersRepository;
        this.totpService = totpService;
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
        UsersDetailEntity usersDetailEntity = new UsersDetailEntity();
        usersDetailEntity.setUsersEntity(usersEntity);
        usersEntity.setUsersDetailEntity(usersDetailEntity);
        usersEntity.setRole("QUEST");
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
        if(usersEntity.isMfa()){
            usersEntity.setSecret(totpService.generateSecret());
        }
        usersRepository.save(usersEntity);
        return UsersMapper.INSTANCE.USERS_DTO(usersEntity);
    }

    @Override
    public boolean CreateImageUsers(Long user_id, MultipartFile multipartFile) throws IOException {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id");
                });
        ImageEntity image = new ImageEntity();
        image.setImage(multipartFile.getBytes());
        image.setName(multipartFile.getName());
        image.setSize(multipartFile.getSize());
        users.setImageEntity(image);
        usersRepository.save(users);
        return true;
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
    public String GenerateQrCode(String secret) {
        return totpService.getUriForImage(secret);
    }

    @Override
    public Boolean VerifyTwoAuth(String username, String code) {
        UsersEntity users = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Not found username"));
        if(!totpService.verifyCode(code, users.getSecret())) {
            throw new RuntimeException("Code is incorrect");
        }
        return true;
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
        users.setMfa(usersEntity.isMfa());
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
