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
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private Integer price;
    @Column(name = "description")
    private String description;
    @Column(name = "img")
    private String img;
    @Column(name = "popularity")
    private Integer popularity;
    @Column(name = "isBasket")
    private Integer isBasket;

    public Pizza(String name, String category, Integer price, String description, String img, Integer popularity,Integer isBasket) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.img = img;
        this.popularity = popularity;
        this.isBasket = isBasket;
    }
}
