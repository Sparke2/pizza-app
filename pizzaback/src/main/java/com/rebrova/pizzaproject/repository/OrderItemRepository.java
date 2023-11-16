package com.rebrova.pizzaproject.repository;

import com.rebrova.pizzaproject.model.Order;
import com.rebrova.pizzaproject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
