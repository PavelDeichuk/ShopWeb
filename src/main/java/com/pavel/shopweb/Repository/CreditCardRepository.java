package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.CreditCardEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity,Long> {

    @Query("SELECT c FROM CreditCardEntity c WHERE c.usersEntity = ?1")
    List<CreditCardEntity> findByUsersEntity(UsersEntity usersEntity);
}
