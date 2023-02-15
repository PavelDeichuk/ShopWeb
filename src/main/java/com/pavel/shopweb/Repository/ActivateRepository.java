package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ActivateEntity;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivateRepository extends JpaRepository<ActivateEntity,Long> {
    @Query("SELECT a FROM ActivateEntity a WHERE a.usersEntity = ?1")
    ActivateEntity findByUsersEntity(UsersEntity usersEntity);

    @Query("SELECT a FROM ActivateEntity a WHERE a.usersEntity = ?1 and a.promoEntity = ?2")
    ActivateEntity findByUsersEntityAndPromoEntity(UsersEntity usersEntity, PromoEntity promoEntity);
}
