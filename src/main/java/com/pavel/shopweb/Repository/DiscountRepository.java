package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<DiscountEntity,Long> {
}
