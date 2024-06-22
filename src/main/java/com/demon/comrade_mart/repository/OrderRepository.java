package com.demon.comrade_mart.repository;

import com.demon.comrade_mart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByProductId(Long productId);
    List<Order> findByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.product.id = :productId")
    List<Order> findOrdersByProductId(@Param("productId") Long productId);

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);
}
