package com.rebrova.pizzaproject.dtos;

import com.rebrova.pizzaproject.model.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer userId;
    private String status;
    private Integer price;
    private List<OrderItem> items;

    public OrderDto(Integer userId, String status,Integer price, List<OrderItem> items) {
        this.userId = userId;
        this.status = status;
        this.price = price;
        this.items = items;
    }
}
