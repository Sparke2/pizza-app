package com.rebrova.pizzaproject.service;

import com.rebrova.pizzaproject.dtos.OrderDto;
import com.rebrova.pizzaproject.dtos.PizzaDto;
import com.rebrova.pizzaproject.exeption.OrderNotFoundException;
import com.rebrova.pizzaproject.exeption.PizzaNotFoundException;
import com.rebrova.pizzaproject.model.Order;
import com.rebrova.pizzaproject.model.Pizza;
import com.rebrova.pizzaproject.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderDto getOrderById(int id) throws OrderNotFoundException;

    OrderDto toDTO(Order order);

    Order toOrder(OrderDto orderDTO);

    List<OrderDto> getAllOrders();

    void deleteOrder(int id);

    List<OrderDto> findAllByOrderByStatus() throws OrderNotFoundException;

    OrderDto createOrder(OrderDto orderDTO);

    public String placeOnOrder(Integer id,List<Integer> list);

    OrderDto updateOrderById(Integer id, OrderDto orderDto) throws OrderNotFoundException;
}
