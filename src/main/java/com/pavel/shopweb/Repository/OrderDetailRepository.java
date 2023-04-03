package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.OrderDetailEntity;
import com.pavel.shopweb.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    @Query("SELECT o FROM OrderDetailEntity o WHERE o.orderEntity = ?1")
    OrderDetailEntity findByOrderEntity(OrderEntity orderEntity);
}
