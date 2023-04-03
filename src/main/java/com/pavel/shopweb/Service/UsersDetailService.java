package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import org.springframework.validation.BindingResult;

public interface UsersDetailService {
    UsersDetailDto GetUserDetailById(Long user_id);

    UsersDetailDto EditUserDetailById(Long user_id, UsersDetailEntity userDetailEntity, BindingResult bindingResult);

    UsersDetailDto DeleteUserDetailById(Long user_id);
}
