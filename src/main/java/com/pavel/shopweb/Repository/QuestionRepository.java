package com.pavel.shopweb.Repository;


import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    @Query("SELECT q FROM QuestionEntity q WHERE q.productEntity = ?1")
    QuestionEntity findByProductEntity(ProductEntity productEntity);
}
