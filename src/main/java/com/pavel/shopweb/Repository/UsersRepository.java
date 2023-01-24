package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    @Query("SELECT u FROM UsersEntity u WHERE u.username = ?1")
    Optional<UsersEntity> findByUsername(String username);

    @Query("SELECT u FROM UsersEntity u WHERE u.email = ?1")
    Optional<UsersEntity> findByEmail(String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.activationToken = ?1")
    Optional<UsersEntity> findByActivation(String activation);

    @Query("SELECT u FROM UsersEntity u WHERE u.resetToken = ?1")
    Optional<UsersEntity> findByResetToken(String resetToken);
}
