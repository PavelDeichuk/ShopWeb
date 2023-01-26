package com.pavel.shopweb.Controller;


import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Service.UsersService;
import com.pavel.shopweb.kafka.Producer;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private static final String USER_ID = "/{user_id}";

    private static final String ACTIVATION = "/activate";

    private static final String RESET = "/reset";

    private static final String CHANGE_PASSWORD = "/change";

    private static final String IMAGE = "/{users_id}/image";

    private static final String IMAGE_EDIT = "/{users_id}/image/{image_id}";

    private static final String USER_QR_CODE = "/qr";

    private final UsersService usersService;

    private final Producer producer;

    public UsersController(UsersService usersService, Producer producer) {
        this.usersService = usersService;
        this.producer = producer;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDto> GetAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size){
        return usersService.GetAllUsers(page,size);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto GetUserById(@PathVariable Long user_id){
        return usersService.GetUserById(user_id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto CreateUser(@RequestBody @Valid UsersEntity usersEntity,
                               BindingResult bindingResult){
        producer.SendMessage("shop1", "test");
        return usersService.CreateUser(usersEntity, bindingResult);
    }

    @RequestMapping(value = ACTIVATION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto ActivateAccountUser(@RequestParam(value = "token", required = true) String activation_code){
        return usersService.ActivateUser(activation_code);
    }

    @RequestMapping(value = USER_QR_CODE,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String GenerateQrCode(@RequestParam(value = "secret", required = true) String secret){
        return usersService.GenerateQrCode(secret);
    }

    @RequestMapping(value = IMAGE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean ImageCreateUser(@PathVariable Long users_id,
                                   @RequestParam(value = "file", required = false)MultipartFile multipartFile) throws IOException {
        return usersService.CreateImageUsers(users_id, multipartFile);
    }

    @RequestMapping(value = IMAGE_EDIT, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean ImageEditUser(@PathVariable Long users_id,
                                 @PathVariable Long image_id,
                                 @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        return usersService.EditImageUsers(users_id, image_id, multipartFile);
    }

    @RequestMapping(value = RESET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto ResetPassword(@RequestParam(value = "email", required = true) String email){
        return usersService.ResetPassword(email);
    }

    @RequestMapping(value = CHANGE_PASSWORD, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto ChangePassword(@RequestParam(value = "token", required = true) String pass_token,
                                   @RequestBody UsersEntity usersEntity){
        return usersService.ChangePassword(pass_token, usersEntity);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto EditUser(@PathVariable Long user_id,
                             @RequestBody UsersEntity usersEntity){
        return usersService.EditUser(user_id,usersEntity);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto DeleteUser(@PathVariable Long user_id){
        return usersService.DeleteUser(user_id);
    }

}
