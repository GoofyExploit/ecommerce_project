package com.ecommerce.ecommerce_springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFixer {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void fixDatabaseSchema() {
        System.out.println("=== Fixing Database Schema ===");
        
        try {
            boolean roleColumnExists = checkColumnExists("users", "role");
            
            if (!roleColumnExists) {
                System.out.println("Adding role column to users table...");
                
                jdbcTemplate.execute("ALTER TABLE users ADD COLUMN role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER'");
                System.out.println("✅ Role column added successfully!");
                
                System.out.println("Updating existing users...");
                
                int adminUpdated = jdbcTemplate.update("UPDATE users SET role = 'ADMIN' WHERE username = 'admin'");
                System.out.println("Updated " + adminUpdated + " admin user(s)");
                
                int usersUpdated = jdbcTemplate.update("UPDATE users SET role = 'USER' WHERE username != 'admin'");
                System.out.println("Updated " + usersUpdated + " regular user(s)");
                
                System.out.println("✅ Database schema fixed successfully!");
            } else {
                System.out.println("✅ Role column already exists!");
            }
            
            verifyDatabaseSchema();
            
        } catch (Exception e) {
            System.out.println("❌ Error fixing database schema: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private boolean checkColumnExists(String tableName, String columnName) {
        try {
            String sql = "SELECT COUNT(*) FROM information_schema.COLUMNS " +
                        "WHERE TABLE_SCHEMA = 'ecommerce' " +
                        "AND TABLE_NAME = ? " +
                        "AND COLUMN_NAME = ?";
            
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tableName, columnName);
            return count != null && count > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    private void verifyDatabaseSchema() {
        try {
            System.out.println("\n=== Verifying Database Schema ===");
            
            var result = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_DEFAULT " +
                "FROM information_schema.COLUMNS " +
                "WHERE TABLE_SCHEMA = 'ecommerce' AND TABLE_NAME = 'users' " +
                "ORDER BY ORDINAL_POSITION"
            );
            
            System.out.println("Users table structure:");
            for (var row : result) {
                System.out.println("  - " + row.get("COLUMN_NAME") + " (" + row.get("DATA_TYPE") + ")");
            }
            
            var users = jdbcTemplate.queryForList("SELECT id, username, role FROM users");
            System.out.println("\nCurrent users:");
            for (var user : users) {
                System.out.println("  - ID: " + user.get("id") + 
                                 ", Username: " + user.get("username") + 
                                 ", Role: " + user.get("role"));
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error verifying schema: " + e.getMessage());
        }
    }
}
