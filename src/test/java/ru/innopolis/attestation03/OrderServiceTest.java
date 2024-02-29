package ru.innopolis.attestation03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.attestation03.model.Order;
import ru.innopolis.attestation03.repository.OrderRepository;
import ru.innopolis.attestation03.service.OrderService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setId(1L);
        order.setStatus("NEW");
    }

    @Test
    public void testCreateOrder() {
        Order newOrder = new Order();
        newOrder.setStatus(order.getStatus());
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Order created = orderService.createOrder(newOrder);
        assertNotNull(created, "Created order should not be null");
        assertEquals(order.getId(), created.getId(), "The ID of created order should match the mock");
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Optional<Order> found = orderService.getOrderById(1L);
        assertTrue(found.isPresent(), "Order should be found");
        assertEquals(order.getId(), found.get().getId(), "Order ID should match");
    }

    @Test
    public void testUpdateOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        order.setStatus("UPDATED");
        Order updated = orderService.updateOrder(order.getId(), order);
        assertNotNull(updated);
        assertEquals("UPDATED", updated.getStatus());
    }

    @Test
    public void testDeleteOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.softDeleteOrder(order.getId());
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();

        assertTrue(capturedOrder.getIsDeleted(), "Order should be marked as deleted");
    }
}
