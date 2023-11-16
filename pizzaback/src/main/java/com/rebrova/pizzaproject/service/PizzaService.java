package com.rebrova.pizzaproject.service;
import com.rebrova.pizzaproject.dtos.PizzaDto;
import com.rebrova.pizzaproject.exeption.PizzaNotFoundException;
import com.rebrova.pizzaproject.model.Pizza;
import java.util.List;
import java.util.Optional;
public interface PizzaService {
    public List<PizzaDto> getAllPizza();
    public PizzaDto findById(int id) throws PizzaNotFoundException;
    List<PizzaDto> findByNameContains(String name) throws PizzaNotFoundException;
    List<PizzaDto> findByPrice(Integer price) throws PizzaNotFoundException;
    void deleteById(int id) throws PizzaNotFoundException;

    PizzaDto toDTO(Pizza pizza);
    PizzaDto updatePizzaById(Integer id, PizzaDto pizzaDto) throws PizzaNotFoundException;
    Pizza toPizza(PizzaDto pizzaDto);
    PizzaDto createPizza(PizzaDto pizzaDto);
    List<PizzaDto> findByCategory(String name) throws PizzaNotFoundException;
   List<PizzaDto> findAllByOrderByPopularityDesc();
   List<PizzaDto> findByIsBasket();
}