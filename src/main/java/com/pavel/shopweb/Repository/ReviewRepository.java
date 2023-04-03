package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    @Query("SELECT r FROM ReviewEntity r WHERE r.productEntity = ?1")
    ReviewEntity findByProductEntity(ProductEntity productEntity);
}
