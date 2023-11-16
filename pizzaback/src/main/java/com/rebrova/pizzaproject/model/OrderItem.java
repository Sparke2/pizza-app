package com.rebrova.pizzaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="amount")
    private Integer amount;

    @Column(name = "total")
    private Integer total;

    @Column(name="pizzaId")
    private Integer pizzaId;

    public OrderItem(Integer amount, Integer total, Integer pizzaId) {
        this.amount = amount;
        this.total = total;
        this.pizzaId = pizzaId;
    }

}
