package ru.innopolis.attestation03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.attestation03.dto.PizzaDTO;
import ru.innopolis.attestation03.model.Pizza;
import ru.innopolis.attestation03.service.PizzaService;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Pizzeria API!";
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        List<PizzaDTO> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        return pizzaService.getPizzaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PizzaDTO> savePizza(@RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO savedPizza = pizzaService.savePizza(pizzaDTO);
        return ResponseEntity.ok(savedPizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable Long id, @RequestBody PizzaDTO pizzaDetailsDTO) {
        return pizzaService.getPizzaById(id)
                .map(pizzaDTO -> {
                    pizzaDTO.setName(pizzaDetailsDTO.getName());
                    pizzaDTO.setDescription(pizzaDetailsDTO.getDescription());
                    pizzaDTO.setPrice(pizzaDetailsDTO.getPrice());
                    PizzaDTO updatedPizzaDTO = pizzaService.savePizza(pizzaDTO);
                    return ResponseEntity.ok(updatedPizzaDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable Long id) {
        return pizzaService.getPizzaById(id)
                .map(pizza -> {
                    pizzaService.deletePizza(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
