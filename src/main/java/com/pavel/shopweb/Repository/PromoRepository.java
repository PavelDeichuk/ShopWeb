package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.PromoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PromoRepository extends JpaRepository<PromoEntity,Long> {
    @Query("SELECT p FROM PromoEntity p WHERE p.name = ?1")
    Optional<PromoEntity> findByName(String name);

}
