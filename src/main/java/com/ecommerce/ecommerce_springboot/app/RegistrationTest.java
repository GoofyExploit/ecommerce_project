package com.ecommerce.ecommerce_springboot.app;

import com.ecommerce.ecommerce_springboot.model.User;
import com.ecommerce.ecommerce_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationTest {
    
    @Autowired
    private UserService userService;
    
    public void testRegistration() {
        System.out.println("=== Testing Registration ===");
        
        try {
            User admin = userService.getUserByUsername("admin");
            System.out.println("Admin user found: " + (admin != null ? "Yes" : "No"));
            if (admin != null) {
                System.out.println("Admin details: " + admin);
            }
        } catch (Exception e) {
            System.out.println("Error checking admin user: " + e.getMessage());
        }
        
        try {
            User testUser = new User(0, "testuser", "testpass", "USER");
            userService.addUser(testUser);
            System.out.println("✅ Test user registration successful!");
            
            User retrievedUser = userService.getUserByUsername("testuser");
            System.out.println("Retrieved user: " + (retrievedUser != null ? "Yes" : "No"));
            if (retrievedUser != null) {
                System.out.println("User details: " + retrievedUser);
            }
        } catch (Exception e) {
            System.out.println("❌ Test user registration failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
