package controller;

import model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PizzaService;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        return pizzaService.getPizzaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pizza createPizza(@RequestBody Pizza pizza) {
        return pizzaService.savePizza(pizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizzaDetails) {
        return pizzaService.getPizzaById(id)
                .map(pizza -> {
                    pizza.setName(pizzaDetails.getName());
                    pizza.setDescription(pizzaDetails.getDescription());
                    pizza.setPrice(pizzaDetails.getPrice());
                    Pizza updatedPizza = pizzaService.savePizza(pizza);
                    return ResponseEntity.ok(updatedPizza);
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
