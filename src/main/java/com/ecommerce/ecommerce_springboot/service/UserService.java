    // Removed misplaced method definition
package com.ecommerce.ecommerce_springboot.service;

import com.ecommerce.ecommerce_springboot.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import java.util.Arrays;

@Service
public class UserService {
    private final WebClient webClient;

    public UserService(@Value("${user.service.url:http://localhost:8081}") String userServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(userServiceUrl + "/users").build();
    }

    public User addUser(User user) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public List<User> getAllUsers() {
        User[] users = webClient.get()
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        return users != null ? Arrays.asList(users) : List.of();
    }

    public User getUserById(String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public User authenticate(String username, String password) {
        java.util.Map<String, String> creds = new java.util.HashMap<>();
        creds.put("username", username);
        creds.put("password", password);
    return webClient.post()
        .uri("/auth/login")
        .bodyValue(creds)
        .retrieve()
        .bodyToMono(User.class)
        .onErrorResume(e -> reactor.core.publisher.Mono.empty())
        .block();
    }

    public User getUserByUsername(String username) {
        return webClient.get()
                .uri("/by-username/{username}", username)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }


    public User updateUser(String id, User user) {
        return webClient.put()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public void deleteUserByUsername(String username) {
        webClient.delete()
                .uri("/by-username/{username}", username)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteUser(String id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
