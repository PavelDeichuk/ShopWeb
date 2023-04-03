package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersDetailRepository extends JpaRepository<UsersDetailEntity,Long> {
    @Query("SELECT u FROM UsersDetailEntity u WHERE u.usersEntity = ?1")
    UsersDetailEntity findByUsersEntity(UsersEntity usersEntity);

    @Query(value = "SELECT * FROM users_detail " +
            "WHERE (SELECT mailing FROM users WHERE mailing = true) " +
            "AND extract(MONTH FROM birthday) = :m " +
            "AND extract(DAY FROM birthday) = :d",
            nativeQuery = true)
    List<UsersDetailEntity> findByMatchMonthAndMatchDay(@Param("m") int month, @Param("d") int day);
}
