package ru.innopolis.attestation03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.attestation03.controller.PizzaController;
import ru.innopolis.attestation03.service.PizzaService;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PizzaController.class)
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @Test
    public void getAllPizzasTest() throws Exception {
        when(pizzaService.getAllPizzas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/pizzas").with(user("user").password("password")))
                .andExpect(status().isOk());

        verify(pizzaService).getAllPizzas();
    }}
