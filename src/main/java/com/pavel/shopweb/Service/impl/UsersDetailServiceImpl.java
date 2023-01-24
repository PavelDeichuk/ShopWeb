package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.UsersDetailMapper;
import com.pavel.shopweb.Repository.UsersDetailRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.UsersDetailService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class UsersDetailServiceImpl implements UsersDetailService {

    private final UsersDetailRepository usersDetailRepository;

    private final UsersRepository usersRepository;

    public UsersDetailServiceImpl(UsersDetailRepository usersDetailRepository, UsersRepository usersRepository) {
        this.usersDetailRepository = usersDetailRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersDetailDto GetUserDetailById(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id!");
                });
        UsersDetailEntity usersDetailEntity = usersDetailRepository.findByUsersEntity(users);
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetailEntity);
    }

    @Override
    public UsersDetailDto EditUserDetailById(Long user_id,
                                             UsersDetailEntity userDetailEntity,
                                             BindingResult bindingResult) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (var errors : fieldErrors){
                throw new BadRequestException(errors.getObjectName() + " " + errors.getDefaultMessage());
            }
        }
        UsersDetailEntity usersDetailEntity = usersDetailRepository.findByUsersEntity(users);
        usersDetailEntity.setAge(userDetailEntity.getAge());
        usersDetailEntity.setDescription(userDetailEntity.getDescription());
        usersDetailEntity.setFirstname(userDetailEntity.getFirstname());
        usersDetailEntity.setSurname(userDetailEntity.getSurname());
        usersDetailEntity.setBirthday(userDetailEntity.getBirthday());
        usersDetailRepository.save(usersDetailEntity);
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetailEntity);
    }

    @Override
    public UsersDetailDto DeleteUserDetailById(Long user_id) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        UsersDetailEntity usersDetailEntity = usersDetailRepository.findByUsersEntity(users);
        usersDetailRepository.deleteById(usersDetailEntity.getId());
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetailEntity);
    }
}
