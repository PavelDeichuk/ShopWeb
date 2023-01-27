package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.name = ?1")
    Optional<ProductEntity> findByName(String name);
}
