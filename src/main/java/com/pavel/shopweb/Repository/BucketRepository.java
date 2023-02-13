package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.BucketEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BucketRepository extends JpaRepository<BucketEntity,Long> {
    @Query("SELECT b FROM BucketEntity b WHERE b.usersEntity = ?1")
    BucketEntity findByUsersEntity(UsersEntity usersEntity);
}
