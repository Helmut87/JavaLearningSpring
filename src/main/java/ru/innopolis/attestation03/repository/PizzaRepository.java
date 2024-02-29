package ru.innopolis.attestation03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    }

