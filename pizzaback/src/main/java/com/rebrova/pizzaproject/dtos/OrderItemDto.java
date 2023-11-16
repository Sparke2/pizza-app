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
public class OrderItemDto {
    private Integer id;
    private Integer amount;
    private Integer total;
    private Integer pizzaId;
}
