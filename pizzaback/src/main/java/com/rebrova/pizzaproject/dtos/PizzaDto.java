package com.rebrova.pizzaproject.dtos;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String img;
    private String category;
    private Integer popularity;
    private Integer isBasket;
}
