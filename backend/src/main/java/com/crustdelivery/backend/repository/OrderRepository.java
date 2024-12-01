package com.crustdelivery.backend.repository;

import com.crustdelivery.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
