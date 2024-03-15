package ru.innopolis.attestation03.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.attestation03.dto.PizzaDTO;
import ru.innopolis.attestation03.model.Pizza;
import ru.innopolis.attestation03.service.PizzaService;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@Tag(name = "Pizza Controller", description = "Контроллер для управления пиццами")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Operation(summary = "Главная страница", description = "Добро пожаловать в API пиццерии!")
    @GetMapping("/")
    public String home() {
        return "Welcome to the Pizzeria API!";
    }

    @Operation(summary = "Получить все пиццы", description = "Получает список всех пицц.")
    @GetMapping
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        List<PizzaDTO> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(pizzas);
    }

    @Operation(summary = "Получить пиццу по ID", description = "Получает пиццу по указанному ID.")
    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        return pizzaService.getPizzaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Сохранить новую пиццу", description = "Сохраняет новую пиццу с предоставленными данными.")
    @PostMapping
    public ResponseEntity<PizzaDTO> savePizza(@RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO savedPizza = pizzaService.savePizza(pizzaDTO);
        return ResponseEntity.ok(savedPizza);
    }

    @Operation(summary = "Обновить пиццу", description = "Обновляет пиццу с указанным ID данными из тела запроса.")
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

    @Operation(summary = "Удалить пиццу", description = "Удаляет пиццу с указанным ID.")
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
