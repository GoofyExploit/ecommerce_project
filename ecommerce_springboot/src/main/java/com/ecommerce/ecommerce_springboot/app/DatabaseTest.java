package com.ecommerce.ecommerce_springboot.app;

import com.ecommerce.ecommerce_springboot.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserDAO userDAO;
    
    public void testDatabaseConnection() {
        System.out.println("=== Testing Database Connection ===");
        
        try {
            String result = jdbcTemplate.queryForObject("SELECT 1", String.class);
            System.out.println("✅ Database connection successful: " + result);
        } catch (Exception e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
            System.out.println("✅ Users table exists with " + count + " records");
        } catch (Exception e) {
            System.out.println("❌ Users table check failed: " + e.getMessage());
            e.printStackTrace();
        }
        
        try {
            var users = userDAO.getAllUsers();
            System.out.println("✅ Retrieved " + users.size() + " users from database");
            for (var user : users) {
                System.out.println("  - " + user.getUsername() + " (" + user.getRole() + ")");
            }
        } catch (Exception e) {
            System.out.println("❌ User retrieval failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
