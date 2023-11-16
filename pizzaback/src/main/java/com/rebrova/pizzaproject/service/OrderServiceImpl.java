package com.rebrova.pizzaproject.service;

import com.rebrova.pizzaproject.dtos.OrderDto;
import com.rebrova.pizzaproject.dtos.PizzaDto;
import com.rebrova.pizzaproject.exeption.OrderNotFoundException;
import com.rebrova.pizzaproject.model.Order;
import com.rebrova.pizzaproject.model.OrderItem;
import com.rebrova.pizzaproject.model.Pizza;
import com.rebrova.pizzaproject.model.User;
import com.rebrova.pizzaproject.repository.OrderRepository;
import com.rebrova.pizzaproject.repository.PizzaRepository;
import com.rebrova.pizzaproject.repository.UserRepository;
import com.rebrova.pizzaproject.responces.PizzaResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PizzaServiceImpl pizzaService;
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public OrderDto getOrderById(int id) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new OrderNotFoundException(id);
        }

        return toDTO(order.get());
    }

    @Override
    public OrderDto toDTO(Order order) {
        Order orderDB = orderRepository.findById(order.getId()).get();
        return OrderDto.builder()
                .id(orderDB.getId())
                .status(orderDB.getStatus())
                .price(orderDB.getPrice())
                .items(orderDB.getItems())
                .userId(orderDB.getUserId())
                .build();
    }

    @Override
    public Order toOrder(OrderDto orderDTO) {
        User user =userService.toUser(userService.getUserById(orderDTO.getUserId()));
        return Order.builder()
                .id(orderDTO.getId())
                .status(orderDTO.getStatus())
                .price(orderDTO.getPrice())
                .items(orderDTO.getItems())
                .userId(orderDTO.getUserId())
                .build();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> findAllByOrderByStatus() throws OrderNotFoundException {
        List<Order> orders = orderRepository.findAllByOrderByStatus();
        List<OrderDto> ordersReturn = null;


        if (orders.isEmpty()) throw new OrderNotFoundException("There is no such order like this");

        for (Order o : orders) {
            ordersReturn.add(toDTO(o));
        }

        return ordersReturn;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDTO) {
        orderRepository.save(toOrder(orderDTO));
        return orderDTO;
    }

    @Override
    @Transactional
    public String placeOnOrder(Integer id,List<Integer> list) {
        int totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        List<Integer> listIdPizza = new ArrayList<>();
        for (Integer i : list) {
            PizzaDto p =pizzaService.findById(i);
            p.setIsBasket(0);
            pizzaRepository.save(pizzaService.toPizza(p));
            totalPrice += p.getPrice();
            if(listIdPizza==null){
                listIdPizza.add(p.getId());
                orderItems.add(new OrderItem(1,p.getPrice(),p.getId()));
            }
            else if(listIdPizza.contains(p.getId())){
                for(OrderItem oi: orderItems){
                    if(oi.getPizzaId() == p.getId()){
                        oi.setAmount(oi.getAmount()+1);
                        oi.setTotal(oi.getTotal()+p.getPrice());
                    }
                }
            }
            else{
                listIdPizza.add(p.getId());
                orderItems.add(new OrderItem(1,p.getPrice(),p.getId()));
            }

        }
        OrderDto order = new OrderDto(id, "Заказ получен", totalPrice, orderItems);
        orderRepository.save(toOrder(order));
        return "Оплата проведена";
    }

    public OrderDto updateOrderById(Integer id, OrderDto orderDto) throws OrderNotFoundException{
        OrderDto orderDb = getOrderById(id);
        if(Objects.nonNull(orderDto.getStatus()) && !"".equalsIgnoreCase(orderDto.getStatus())){
            orderDb.setStatus(orderDto.getStatus());
        }
        if(Objects.nonNull(orderDto.getUserId())&&(orderDto.getUserId()) != 0){
            orderDb.setUserId(orderDto.getUserId());
        }
        if(Objects.nonNull(orderDto.getItems())){
            orderDb.setItems(orderDto.getItems());
        }
        orderRepository.save(toOrder(orderDb));
        return orderDb;
    }


}
