package repository;

import model.Ingredient;
import model.Order;
import model.Pizza;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    }

