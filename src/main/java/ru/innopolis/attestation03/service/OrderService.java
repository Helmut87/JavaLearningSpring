package ru.innopolis.attestation03.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.attestation03.model.Order;
import ru.innopolis.attestation03.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByIsDeleted(false);
    }

    @Transactional
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(updatedOrder.getStatus());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    updatedOrder.setId(id);
                    return orderRepository.save(updatedOrder);
                });
    }

    @Transactional
    public void softDeleteOrder(Long id) {
        orderRepository.findById(id)
                .ifPresent(order -> {
                    order.setIsDeleted(true); // Установка флага isDeleted в true
                    orderRepository.save(order);
                });
    }
}