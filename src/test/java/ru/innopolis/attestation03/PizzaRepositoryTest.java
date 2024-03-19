package ru.innopolis.attestation03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.innopolis.attestation03.model.Pizza;
import ru.innopolis.attestation03.repository.PizzaRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void saveNewPizzaTest() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setName("Margherita");
        pizza.setDescription("Classic");
        pizza.setPrice(10.0);
        Pizza savedPizza = pizzaRepository.save(pizza);

        assertNotNull(savedPizza.getId());
        assertEquals("Margherita", savedPizza.getName());
    }

    @Test
    public void shouldSavePizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Hawaiian");
        pizza.setDescription("Classic Hawaiian pizza with pineapple and ham");
        pizza.setPrice(12.5);

        Pizza savedPizza = pizzaRepository.save(pizza);

        assertThat(savedPizza).isNotNull();
        assertThat(savedPizza.getId()).isNotNull();
        assertThat(savedPizza.getName()).isEqualTo("Hawaiian");
    }

    @Test
    public void shouldFindPizzaById() {
        Pizza pizza = new Pizza();
        pizza.setName("Pepperoni");
        pizza.setDescription("Pepperoni pizza");
        pizza.setPrice(15.0);

        Pizza savedPizza = pizzaRepository.save(pizza);
        Optional<Pizza> foundPizza = pizzaRepository.findById(savedPizza.getId());

        assertThat(foundPizza).isPresent();
        assertThat(foundPizza.get().getName()).isEqualTo("Pepperoni");
    }

    @Test
    public void shouldUpdatePizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Margherita");
        pizza.setDescription("Margherita pizza with tomato and basil");
        pizza.setPrice(10.0);

        Pizza savedPizza = pizzaRepository.save(pizza);

        savedPizza.setDescription("Updated description");
        savedPizza.setPrice(11.0);
        Pizza updatedPizza = pizzaRepository.save(savedPizza);

        assertThat(updatedPizza.getDescription()).isEqualTo("Updated description");
        assertThat(updatedPizza.getPrice()).isEqualTo(11.0);
    }

    @Test
    public void shouldDeletePizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Veggie");
        pizza.setDescription("Vegetarian pizza");
        pizza.setPrice(12.0);

        Pizza savedPizza = pizzaRepository.save(pizza);
        assertThat(pizzaRepository.existsById(savedPizza.getId())).isTrue();

        pizzaRepository.delete(savedPizza);

        assertThat(pizzaRepository.existsById(savedPizza.getId())).isFalse();
    }
}

