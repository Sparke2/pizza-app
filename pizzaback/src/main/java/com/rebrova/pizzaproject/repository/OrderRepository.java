package com.rebrova.pizzaproject.repository;

import com.rebrova.pizzaproject.model.Order;
import com.rebrova.pizzaproject.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderByStatus();
}
