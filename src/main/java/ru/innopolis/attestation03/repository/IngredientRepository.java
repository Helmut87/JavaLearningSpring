package ru.innopolis.attestation03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
