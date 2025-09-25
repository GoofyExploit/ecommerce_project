package com.ecommerce.user.controller;

import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Run manually by an ADMIN to migrate any plaintext passwords to BCrypt hashes
    @PostMapping("/migrate-hash-passwords")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> migratePasswords() {
        List<User> users = userRepository.findAll();
        List<String> migratedUsernames = new ArrayList<>();

        for (User u : users) {
            String pw = u.getPassword();
            if (pw == null || pw.isEmpty()) continue;
            // If it already looks like a BCrypt hash, skip
            if (pw.startsWith("$2a$") || pw.startsWith("$2b$") || pw.startsWith("$2y$")) continue;
            // Otherwise hash and save
            String hashed = passwordEncoder.encode(pw);
            u.setPassword(hashed);
            userRepository.save(u);
            migratedUsernames.add(u.getUsername());
        }

        java.util.Map<String,Object> result = new java.util.HashMap<>();
        result.put("migratedCount", migratedUsernames.size());
        result.put("usernames", migratedUsernames);
        return ResponseEntity.ok(result);
    }
}
