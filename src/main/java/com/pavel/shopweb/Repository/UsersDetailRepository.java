package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersDetailRepository extends JpaRepository<UsersDetailEntity,Long> {
    @Query("SELECT u FROM UsersDetailEntity u WHERE u.usersEntity = ?1")
    UsersDetailEntity findByUsersEntity(UsersEntity usersEntity);
}
