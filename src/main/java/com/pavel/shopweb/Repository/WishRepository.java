package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Entity.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishRepository extends JpaRepository<WishEntity,Long> {
    @Query("SELECT w FROM WishEntity w WHERE w.usersEntity = ?1")
    WishEntity findByUsersEntity(UsersEntity usersEntity);
}
