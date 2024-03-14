package ru.innopolis.attestation03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.attestation03.dto.PizzaDTO;
import ru.innopolis.attestation03.model.Pizza;
import ru.innopolis.attestation03.repository.PizzaRepository;
import ru.innopolis.attestation03.service.PizzaService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    private Pizza pizza;
    private PizzaDTO pizzaDTO;

    @BeforeEach
    public void setUp() {
        pizza = new Pizza();
        pizza.setId(1L);
        pizza.setName("Margherita");
        pizza.setDescription("Classic");
        pizza.setPrice(10.0);

        pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setDescription(pizza.getDescription());
        pizzaDTO.setPrice(pizza.getPrice());
    }

    @Test
    public void testGetAllPizzas() {
        when(pizzaRepository.findAll()).thenReturn(Collections.singletonList(pizza));
        List<PizzaDTO> pizzas = pizzaService.getAllPizzas();
        assertNotNull(pizzas, "List of pizzas should not be null");
        assertFalse(pizzas.isEmpty(), "List of pizzas should not be empty");
        assertEquals(1, pizzas.size(), "List of pizzas should contain exactly 1 pizza");
        assertEquals(pizzaDTO.getName(), pizzas.get(0).getName(), "The name of the pizza in the list should match the mock pizzaDTO");
    }

    @Test
    public void testGetPizzaById() {
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
        Optional<PizzaDTO> found = pizzaService.getPizzaById(1L);
        assertTrue(found.isPresent(), "Pizza should be found");
        assertEquals(pizzaDTO.getId(), found.get().getId(), "The ID of the pizza should match the mock");
    }

    @Test
    public void testSavePizza() {
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        PizzaDTO saved = pizzaService.savePizza(pizzaDTO);
        assertNotNull(saved, "Saved pizza should not be null");
        assertEquals(pizzaDTO.getId(), saved.getId(), "The ID of the saved pizza should match the mock");
    }

    @Test
    public void testDeletePizza() {
        doNothing().when(pizzaRepository).deleteById(anyLong());
        pizzaService.deletePizza(1L);
        verify(pizzaRepository).deleteById(1L);
    }
}
