package com.example.testwiron.controller;

import com.example.testwiron.model.Order;
import com.example.testwiron.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderController(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    // Create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        order.getProductIds().forEach(productId -> {
            String productUrl = "http://localhost:8080/products/" + productId;
            try {
                restTemplate.getForObject(productUrl, Object.class); // Replace Object.class with your Product class
            } catch (Exception e) {
                throw new RuntimeException("Product with ID " + productId + " not found");
            }
        });
        return orderRepository.save(order);
    }

    // Retrieve all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve a single order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Update an existing order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderRepository.save(order);
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
