package ru.innopolis.attestation03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIsDeleted(boolean isDeleted);
}
