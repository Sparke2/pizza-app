package com.rebrova.pizzaproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

@Table(name = "appUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private List<Order> orders;

    public User(String name, String phone, String address, List<Order> orders) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orders = orders;
    }
}
