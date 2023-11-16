package com.rebrova.pizzaproject.controller;

import com.rebrova.pizzaproject.dtos.OrderDto;
import com.rebrova.pizzaproject.exeption.OrderNotFoundException;
import com.rebrova.pizzaproject.model.Order;
import com.rebrova.pizzaproject.model.Pizza;
import com.rebrova.pizzaproject.model.User;
import com.rebrova.pizzaproject.repository.OrderRepository;
import com.rebrova.pizzaproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable("id") int id) throws OrderNotFoundException{
        return orderService.getOrderById(id);
    }
    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
    }
    @GetMapping("/byStatus")
    public List<OrderDto> findAllByOrderByStatus() throws OrderNotFoundException{
        return orderService.findAllByOrderByStatus();
    }
    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDTO){
        System.out.println("create order");
        return orderService.createOrder(orderDTO);
    }
    @PostMapping("/placeOnOrder/{id}")
    public String placeOnOrder(@PathVariable("id") Integer id, @RequestBody List<Integer> list){
        return orderService.placeOnOrder(id,list);
    }
    @PutMapping("/{id}")
    public OrderDto updateOrderById(@PathVariable("id") Integer id, @RequestBody OrderDto orderDto) throws OrderNotFoundException{
        return orderService.updateOrderById(id, orderDto);
    }

}
