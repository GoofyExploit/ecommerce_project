package com.ecommerce.order.controller;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // Defensive: ensure items is not null
        if (order.getItems() == null) {
            order.setItems(new java.util.ArrayList<>());
        }
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable String id) {
        return orderRepository.findById(id);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderRepository.deleteById(id);
    }
}
