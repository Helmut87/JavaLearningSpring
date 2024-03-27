package ru.innopolis.attestation03;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.attestation03.controller.PizzaController;
import ru.innopolis.attestation03.dto.PizzaDTO;
import ru.innopolis.attestation03.service.PizzaService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class PizzaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PizzaService pizzaService;

    @InjectMocks
    private PizzaController pizzaController;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(pizzaController).build();
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllPizzas() throws Exception {
        List<PizzaDTO> mockPizzas = List.of(new PizzaDTO(/* parameters */));
        when(pizzaService.getAllPizzas()).thenReturn(mockPizzas);

        mockMvc.perform(get("/api/pizzas"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockPizzas)));

        verify(pizzaService).getAllPizzas();
    }

    @Test
    public void testGetPizzaById() throws Exception {
        Long pizzaId = 1L;
        PizzaDTO mockPizza = new PizzaDTO(/* parameters */);
        when(pizzaService.getPizzaById(pizzaId)).thenReturn(Optional.of(mockPizza));

        mockMvc.perform(get("/api/pizzas/{id}", pizzaId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockPizza)));

        verify(pizzaService).getPizzaById(pizzaId);
    }

    @Test
    public void testSavePizza() throws Exception {
        PizzaDTO newPizza = new PizzaDTO(/* parameters */);
        PizzaDTO savedPizza = new PizzaDTO(/* parameters */);
        when(pizzaService.savePizza(any(PizzaDTO.class))).thenReturn(savedPizza);

        mockMvc.perform(post("/api/pizzas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPizza)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(savedPizza)));

        verify(pizzaService).savePizza(any(PizzaDTO.class));
    }

    @Test
    public void testUpdatePizza() throws Exception {
        Long pizzaId = 1L;
        PizzaDTO updateInfo = new PizzaDTO(/* parameters for update */);
        PizzaDTO updatedPizza = new PizzaDTO(/* parameters after update */);

        when(pizzaService.getPizzaById(pizzaId)).thenReturn(Optional.of(new PizzaDTO(/* original */)));
        when(pizzaService.savePizza(any(PizzaDTO.class))).thenReturn(updatedPizza);

        mockMvc.perform(put("/api/pizzas/{id}", pizzaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateInfo)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedPizza)));

        verify(pizzaService).savePizza(any(PizzaDTO.class));
    }

    @Test
    public void testDeletePizza() throws Exception {
        Long pizzaId = 1L;
        when(pizzaService.getPizzaById(pizzaId)).thenReturn(Optional.of(new PizzaDTO(/* parameters */)));

        mockMvc.perform(delete("/api/pizzas/{id}", pizzaId))
                .andExpect(status().isOk());

        verify(pizzaService).deletePizza(pizzaId);
    }
}
