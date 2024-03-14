package ru.innopolis.attestation03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.attestation03.dto.PizzaDTO;
import ru.innopolis.attestation03.model.Pizza;
import ru.innopolis.attestation03.repository.PizzaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaDTO> getAllPizzas() {
        return pizzaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PizzaDTO> getPizzaById(Long id) {
        return pizzaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PizzaDTO savePizza(PizzaDTO pizzaDTO) {
        Pizza pizza = convertToEntity(pizzaDTO);
        Pizza savedPizza = pizzaRepository.save(pizza);
        return convertToDTO(savedPizza);
    }

    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }

    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setDescription(pizza.getDescription());
        pizzaDTO.setPrice(pizza.getPrice());
        return pizzaDTO;
    }

    private Pizza convertToEntity(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setId(pizzaDTO.getId());
        pizza.setName(pizzaDTO.getName());
        pizza.setDescription(pizzaDTO.getDescription());
        pizza.setPrice(pizzaDTO.getPrice());
        return pizza;
    }
}