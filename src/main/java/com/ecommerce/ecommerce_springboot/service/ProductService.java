    // Removed misplaced method definition
package com.ecommerce.ecommerce_springboot.service;

import com.ecommerce.ecommerce_springboot.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import java.util.Arrays;

@Service
public class ProductService {
    private final WebClient webClient;

    public ProductService(@Value("${product.service.url:http://localhost:8082}") String productServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(productServiceUrl + "/products").build();
    }

    public Product addProduct(Product product) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public List<Product> getAllProducts() {
        Product[] products = webClient.get()
                .retrieve()
                .bodyToMono(Product[].class)
                .block();
        return products != null ? Arrays.asList(products) : List.of();
    }

    public Product getProductById(String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }


    public Product updateProduct(String id, Product product) {
        return webClient.put()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product getProductByName(String name) {
        return webClient.get()
                .uri("/by-name/{name}", name)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public void deleteProductByName(String name) {
        webClient.delete()
                .uri("/by-name/{name}", name)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteProduct(String id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
