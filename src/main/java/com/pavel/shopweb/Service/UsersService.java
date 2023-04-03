package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UsersService {
    List<UsersDto> GetAllUsers(int page, int size);

    UsersDto GetUserById(Long user_id);

    UsersDto CreateUser(UsersEntity usersEntity, BindingResult bindingResult);

    boolean CreateImageUsers(Long user_id, MultipartFile multipartFile) throws IOException;

    boolean EditImageUsers(Long users_id, Long image_id, MultipartFile multipartFile) throws Exception;

    UsersDto ActivateUser(String activationCode);

    String GenerateQrCode(String secret);

    Boolean VerifyTwoAuth(String username, String code);

    UsersDto ResetPassword(String email);

    UsersDto ChangePassword(String passToken, UsersEntity usersEntity);

    UsersDto EditUser(Long user_id, UsersEntity usersEntity);

    UsersDto DeleteUser(Long user_id);
}
