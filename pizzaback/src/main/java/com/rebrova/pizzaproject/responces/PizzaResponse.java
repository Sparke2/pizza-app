package com.rebrova.pizzaproject.responces;

import com.rebrova.pizzaproject.model.Pizza;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaResponse {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String img;
    private String category;
    private Integer popularity;
    public static PizzaResponse cast(Pizza pizza){
        return PizzaResponse.builder()
                .id(pizza.getId())
                .category(pizza.getCategory())
                .name(pizza.getName())
                .price(pizza.getPrice())
                .description(pizza.getDescription())
                .img(pizza.getImg())
                .popularity(pizza.getPopularity())
                .build();
    }
}
