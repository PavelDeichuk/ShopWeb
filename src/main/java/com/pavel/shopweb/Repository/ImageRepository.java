package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    @Query("SELECT i FROM ImageEntity i WHERE i.name = ?1")
    Optional<ImageEntity> findByName(String name);

}
