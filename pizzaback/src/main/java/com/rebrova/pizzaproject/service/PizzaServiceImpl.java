package com.rebrova.pizzaproject.service;
import com.rebrova.pizzaproject.dtos.PizzaDto;
import com.rebrova.pizzaproject.exeption.PizzaNotFoundException;
import com.rebrova.pizzaproject.model.Pizza;
import com.rebrova.pizzaproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Override
    public PizzaDto createPizza(PizzaDto pizzaDto) {
        pizzaRepository.save(toPizza(pizzaDto));
        return pizzaDto;
    }

    @Override
    public List<PizzaDto> findByCategory(String category) throws PizzaNotFoundException {
        List<PizzaDto> pizzas = pizzaRepository.findByCategory(category).stream().map(this::toDTO).toList();
        if(pizzas.isEmpty()) throw new PizzaNotFoundException("no such pizzas");
        return pizzas;
    }

    @Override
    public List<PizzaDto> findAllByOrderByPopularityDesc() {
        List<PizzaDto> pizzas =  pizzaRepository.findAllByOrderByPopularityDesc().stream().map(this::toDTO).toList();
        return pizzas;
    }

    @Override
    public List<PizzaDto> findByIsBasket() {
        List<PizzaDto> pizzas = pizzaRepository.findByIsBasket(1).stream().map(this::toDTO).toList();
        List<PizzaDto> pizzasRes = new ArrayList<>();
        for(PizzaDto p: pizzas){
            if(p.getIsBasket()!=null){
                if(p.getIsBasket()==1) pizzasRes.add(p);
            }
        }
        return pizzasRes;
    }

    @Override
    public List<PizzaDto> getAllPizza() {
        return pizzaRepository.findAll().stream().map(this::toDTO).toList();
    }
    @Override
    public PizzaDto findById(int id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if(pizza.isEmpty()) throw new PizzaNotFoundException(id);
        return toDTO(pizza.get());
    }
    @Override
    public List<PizzaDto> findByNameContains(String name) throws PizzaNotFoundException {
        List<PizzaDto> pizzas = pizzaRepository.findByNameContains(name).stream().map(this::toDTO).toList();
        if(pizzas.isEmpty()) throw new PizzaNotFoundException("no such pizzas");
        return pizzas;
    }
    @Override
    public List<PizzaDto> findByPrice(Integer price) throws PizzaNotFoundException {
        List<PizzaDto> pizzas = pizzaRepository.findByPrice(price).stream().map(this::toDTO).toList();
        if(pizzas.isEmpty()) throw new PizzaNotFoundException("no such pizzas");
        return pizzas;
    }
    @Override
    public PizzaDto updatePizzaById(Integer id, PizzaDto pizzaDto) throws PizzaNotFoundException {
       PizzaDto pizzaDb = findById(id);
        if(Objects.nonNull(pizzaDto.getDescription()) && !"".equalsIgnoreCase(pizzaDto.getDescription())){
            pizzaDb.setDescription(pizzaDto.getDescription());
        }
        if(Objects.nonNull(pizzaDto.getName()) && !"".equalsIgnoreCase(pizzaDto.getName())){
            pizzaDb.setName(pizzaDto.getName());
        }
        if(Objects.nonNull(pizzaDto.getImg()) && !"".equalsIgnoreCase(pizzaDto.getImg())){
            pizzaDb.setImg(pizzaDto.getImg());
        }
        if(Objects.nonNull(pizzaDto.getPrice()) && (pizzaDto.getPrice()) != 0){
            pizzaDb.setPrice(pizzaDto.getPrice());
        }
        if(Objects.nonNull(pizzaDto.getCategory())&& !"".equalsIgnoreCase(pizzaDto.getCategory())){
            pizzaDb.setCategory(pizzaDto.getCategory());
        }
        if(Objects.nonNull(pizzaDto.getIsBasket())){
            pizzaDb.setIsBasket(pizzaDto.getIsBasket());
        }
        pizzaRepository.save(toPizza(pizzaDb));
        return pizzaDb;
    }
    @Override
    public void deleteById(int id)throws PizzaNotFoundException {
        if(pizzaRepository.findById(id).isEmpty()) throw new PizzaNotFoundException(id);
        pizzaRepository.deleteById(id);
    }

    @Override
    public PizzaDto toDTO(Pizza pizza) {
        return PizzaDto.builder()
                .id(pizza.getId())
                .name(pizza.getName())
                .img(pizza.getImg())
                .description(pizza.getDescription())
                .price(pizza.getPrice())
                .category(pizza.getCategory())
                .popularity(pizza.getPopularity())
                .isBasket(pizza.getIsBasket())
                .build();
    }

    @Override
    public Pizza toPizza(PizzaDto pizzaDto) {
        return Pizza.builder()
                .id(pizzaDto.getId())
                .name(pizzaDto.getName())
                .img(pizzaDto.getImg())
                .description(pizzaDto.getDescription())
                .price(pizzaDto.getPrice())
                .category(pizzaDto.getCategory())
                .popularity(pizzaDto.getPopularity())
                .isBasket(pizzaDto.getIsBasket())
                .build();
    }
}