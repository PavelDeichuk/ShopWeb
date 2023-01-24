package com.pavel.shopweb.Security;

import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserSecurityService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity users = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found username for authentication provider");
                });
        UserDetails userDetails = User
                .builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .roles(users.getRole())
                .build();
        return userDetails;
    }
}
