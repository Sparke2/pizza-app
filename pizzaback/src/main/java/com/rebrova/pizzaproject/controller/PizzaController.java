package com.rebrova.pizzaproject.controller;

import com.rebrova.pizzaproject.dtos.PizzaDto;
import com.rebrova.pizzaproject.model.Pizza;
import com.rebrova.pizzaproject.exeption.PizzaNotFoundException;
import com.rebrova.pizzaproject.repository.PizzaRepository;
import com.rebrova.pizzaproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza")
@CrossOrigin
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<PizzaDto> getAllPizza(){
        return pizzaService.getAllPizza();
    }
    @PostMapping("/createPizza")
    public PizzaDto createPizza(@RequestBody PizzaDto pizzaDto){
        System.out.println("create pizza");
        return pizzaService.createPizza(pizzaDto);
    }
    @GetMapping("/findByCategory")
    public List<PizzaDto> findByCategory(@RequestParam("category") String category)throws PizzaNotFoundException{
        return pizzaService.findByCategory(category);
    }
    @GetMapping("/findByPopularity")
    public List<PizzaDto> findAllByOrderByPopularityDesc(){
        return pizzaService.findAllByOrderByPopularityDesc();
    }
    @GetMapping("/{id}")
    public PizzaDto findById(@PathVariable("id") int id) throws PizzaNotFoundException{
        return pizzaService.findById(id);
    }
    @GetMapping("/findByName")
    public List<PizzaDto> findByNameContains(@RequestParam("name") String name) throws PizzaNotFoundException{
        return pizzaService.findByNameContains(name);
    }
    @GetMapping("/findByPrice")
    public List<PizzaDto> findByPrice(@RequestParam("price") Integer price) throws PizzaNotFoundException{
        return pizzaService.findByPrice(price);
    }
    @PutMapping("/{id}")
    public PizzaDto updatePizzaById(@PathVariable("id") Integer id,@RequestBody PizzaDto pizzaDto) throws PizzaNotFoundException{
        return pizzaService.updatePizzaById(id, pizzaDto);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id)throws PizzaNotFoundException{
        pizzaService.deleteById(id);
    }
    @GetMapping("/findByIsBasket")
    public List<PizzaDto> findByIsBasket() {
        return pizzaService.findByIsBasket();
    }
    @PutMapping("/setIsBasket/{id}")
    public PizzaDto updateIsBasket(@PathVariable("id") Integer id){
        PizzaDto p = new PizzaDto(null,null,null,null,null,null,null,1);
        return pizzaService.updatePizzaById(id, p);
    }
    @PutMapping("/deleteFromBasket/{id}")
    public PizzaDto deleteFromBasket(@PathVariable("id") Integer id){
        PizzaDto p = new PizzaDto(null,null,null,null,null,null,null,0);
        return pizzaService.updatePizzaById(id, p);
    }
}
