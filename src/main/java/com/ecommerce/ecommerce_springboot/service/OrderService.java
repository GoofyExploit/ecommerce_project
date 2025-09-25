package com.ecommerce.ecommerce_springboot.service;

import com.ecommerce.ecommerce_springboot.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import java.util.Arrays;

@Service
public class OrderService {
    private final WebClient webClient;

    public OrderService(@Value("${order.service.url:http://localhost:8083}") String orderServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(orderServiceUrl + "/orders").build();
    }

    public Order addOrder(Order order) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    public List<Order> getAllOrders() {
        Order[] orders = webClient.get()
                .retrieve()
                .bodyToMono(Order[].class)
                .block();
        return orders != null ? Arrays.asList(orders) : List.of();
    }

    public Order getOrderById(String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        Order[] orders = webClient.get()
                .uri("/by-customer/{customerId}", customerId)
                .retrieve()
                .bodyToMono(Order[].class)
                .block();
        return orders != null ? Arrays.asList(orders) : List.of();
    }

    public Order updateOrder(String id, Order order) {
        return webClient.put()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    public void deleteOrder(String id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
