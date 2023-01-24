package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Model.JwtRequest;
import com.pavel.shopweb.Model.JwtResponse;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Security.UserSecurityService;
import com.pavel.shopweb.Service.UsersService;
import com.pavel.shopweb.Util.JwtUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private static final String TOKEN = "/auth";

    private final JwtUtility jwtUtil;

    private final UserSecurityService userSecurityService;

    private final UsersRepository usersRepository;

    private final UsersService usersService;

    public TokenController(JwtUtility jwtUtil, UserSecurityService userSecurityService, UsersRepository usersRepository, UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.userSecurityService = userSecurityService;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    @RequestMapping(TOKEN)
    public ResponseEntity<?> GetToken(@RequestBody JwtRequest jwtRequest,
                                      @RequestParam(value = "code", required = false, defaultValue = "0") String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        final UserDetails userDetails = userSecurityService.loadUserByUsername(jwtRequest.getUsername());
        UsersEntity users = usersRepository.findByUsername(jwtRequest.getUsername()).orElseThrow();
        if(users.getMfa()){
            usersService.VerifyTwoAuth(jwtRequest.getUsername(), code);
        }
        final String token = jwtUtil.generateToken(userDetails);
        JwtResponse tokens = new JwtResponse(token);
        return new ResponseEntity(tokens, httpHeaders, HttpStatus.OK);
    }
}
