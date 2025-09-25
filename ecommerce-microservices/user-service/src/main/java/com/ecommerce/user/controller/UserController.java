package com.ecommerce.user.controller;

import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping
    public User createUser(@RequestBody User user) {
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        User saved = userRepository.save(user);
        saved.setPassword(null);
        return saved;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        // Merge with existing user so we don't wipe the password when empty
        Optional<User> existingOpt = userRepository.findById(id);
        if (existingOpt.isPresent()) {
            User existing = existingOpt.get();
            existing.setUsername(user.getUsername() != null ? user.getUsername() : existing.getUsername());
            existing.setRole(user.getRole() != null ? user.getRole() : existing.getRole());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existing.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(existing);
            existing.setPassword(null);
            return existing;
        } else {
            // fallback: create/replace
            user.setId(id);
            if (user.getRole() == null) {
                user.setRole("USER");
            }
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            User saved = userRepository.save(user);
            saved.setPassword(null);
            return saved;
        }
    }

    @DeleteMapping("/by-username/{username}")
    public void deleteUserByUsername(@PathVariable String username) {
        userRepository.deleteByUsername(username);
    }

    @GetMapping("/by-username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("/auth/login")
    public org.springframework.http.ResponseEntity<User> login(@RequestBody java.util.Map<String, String> creds) {
        String username = creds.get("username");
        String password = creds.get("password");
        if (username == null || password == null) return org.springframework.http.ResponseEntity.badRequest().build();
        User u = userRepository.findByUsername(username);
        if (u == null) return org.springframework.http.ResponseEntity.status(401).build();
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        if (u.getPassword() != null && encoder.matches(password, u.getPassword())) {
            u.setPassword(null);
            return org.springframework.http.ResponseEntity.ok(u);
        }
        return org.springframework.http.ResponseEntity.status(401).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }
}
