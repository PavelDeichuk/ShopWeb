package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.UsersDetailDto;
import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Service.UsersDetailService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users-detail")
public class UsersDetailController {

    private static final String USERS_ID = "/{user_id}";

    private final UsersDetailService usersDetailService;

    public UsersDetailController(UsersDetailService usersDetailService) {
        this.usersDetailService = usersDetailService;
    }
    @RequestMapping(value = USERS_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto GetUsersDetailById(@PathVariable Long user_id){
        return usersDetailService.GetUserDetailById(user_id);
    }

    @RequestMapping(value = USERS_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto EditUserDetail(@PathVariable Long user_id,
                                         @RequestBody @Valid UsersDetailEntity usersDetailEntity,
                                         BindingResult bindingResult){
        return usersDetailService.EditUserDetailById(user_id, usersDetailEntity, bindingResult);
    }

    @RequestMapping(value = USERS_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto DeleteUserDetail(@PathVariable Long user_id){
        return usersDetailService.DeleteUserDetailById(user_id);
    }
}
