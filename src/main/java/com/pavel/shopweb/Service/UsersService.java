package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UsersService {
    List<UsersDto> GetAllUsers(int page, int size);

    UsersDto GetUserById(Long user_id);

    UsersDto CreateUser(UsersEntity usersEntity, BindingResult bindingResult);

    UsersDto ActivateUser(String activationCode);

    String GenerateQrCode(String secret);

    Boolean VerifyTwoAuth(String username, String code);

    UsersDto ResetPassword(String email);

    UsersDto ChangePassword(String passToken, UsersEntity usersEntity);

    UsersDto EditUser(Long user_id, UsersEntity usersEntity);

    UsersDto DeleteUser(Long user_id);
}
