package com.ecommerce.ecommerce_springboot.app;

import com.ecommerce.ecommerce_springboot.model.User;
import com.ecommerce.ecommerce_springboot.model.Order;
import com.ecommerce.ecommerce_springboot.model.OrderItem;
import com.ecommerce.ecommerce_springboot.model.Product;
import com.ecommerce.ecommerce_springboot.service.ProductService;
import com.ecommerce.ecommerce_springboot.service.UserService;
import com.ecommerce.ecommerce_springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser = null;
    
    @Autowired
    private DatabaseTest databaseTest;

    public ConsoleApp(ProductService productService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    public void start() {
        System.out.println("=== Welcome to E-Commerce System ===");
        
        // Main application loop - handles both login and main app
        while (true) {
            // Login loop
            while (currentUser == null) {
                System.out.println("\n=== LOGIN ===");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Test Database Connection");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1 -> login();
                    case 2 -> register();
                    case 3 -> {
                        databaseTest.testDatabaseConnection();
                    }
                    case 4 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
            
            // Main application loop (when logged in)
            while (currentUser != null) {
                if (currentUser.isAdmin()) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
            }
        }
    }

    // Login and Registration methods
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        try {
            User user = userService.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("✅ Login successful! Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
            } else {
                System.out.println("❌ Invalid username or password!");
            }
        } catch (Exception e) {
            System.out.println("❌ Login failed: " + e.getMessage());
            e.printStackTrace(); // For debugging
        }
    }
    
    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Ask for role
        System.out.println("Select role:");
        System.out.println("1. User (default)");
        System.out.println("2. Admin");
        System.out.print("Enter choice (1 or 2): ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        String role = "USER"; // default
        if (roleChoice == 2) {
            role = "ADMIN";
        }
        
        // Validate input
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            System.out.println("❌ Username and password cannot be empty!");
            return;
        }
        
        if (username.length() < 3) {
            System.out.println("❌ Username must be at least 3 characters long!");
            return;
        }
        
        if (password.length() < 3) {
            System.out.println("❌ Password must be at least 3 characters long!");
            return;
        }
        
        try {
            // Check if user already exists
            User existingUser = userService.getUserByUsername(username.trim());
            if (existingUser != null) {
                System.out.println("❌ Username already exists!");
                return;
            }
            
            // Create new user
            User newUser = new User(0, username.trim(), password, role);
            userService.addUser(newUser);
            System.out.println("✅ Registration successful! Role: " + role + ". Please login.");
            
        } catch (Exception e) {
            System.out.println("❌ Registration failed: " + e.getMessage());
            System.out.println("Error details: " + e.getClass().getSimpleName());
            e.printStackTrace(); // For debugging
        }
    }
    
    // Admin Menu
    private void showAdminMenu() {
        System.out.println("\n=== ADMIN DASHBOARD ===");
        System.out.println("Welcome, " + currentUser.getUsername() + " (ADMIN)");
        System.out.println("=== PRODUCT MANAGEMENT ===");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("=== USER MANAGEMENT ===");
        System.out.println("5. View All Users");
        System.out.println("6. Update User");
        System.out.println("7. Delete User");
        System.out.println("=== ORDER MANAGEMENT ===");
        System.out.println("8. View All Orders");
        System.out.println("9. Update Order");
        System.out.println("10. Delete Order");
        System.out.println("=== ACCOUNT ===");
        System.out.println("11. Logout");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> viewProducts();
            case 3 -> updateProduct();
            case 4 -> deleteProduct();
            case 5 -> viewUsers();
            case 6 -> updateUser();
            case 7 -> deleteUser();
            case 8 -> viewOrders();
            case 9 -> updateOrder();
            case 10 -> deleteOrder();
            case 11 -> {
                currentUser = null;
                System.out.println("✅ Logged out successfully!");
                return; // Return to login screen
            }
            case 12 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice! Please try again.");
        }
    }
    
    // User Menu
    private void showUserMenu() {
        System.out.println("\n=== USER DASHBOARD ===");
        System.out.println("Welcome, " + currentUser.getUsername() + " (USER)");
        System.out.println("=== SHOPPING ===");
        System.out.println("1. View All Products");
        System.out.println("2. Place Order");
        System.out.println("3. View My Orders");
        System.out.println("=== ACCOUNT ===");
        System.out.println("4. Update Profile");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1 -> viewProducts();
            case 2 -> placeOrder();
            case 3 -> viewMyOrders();
            case 4 -> updateMyProfile();
            case 5 -> {
                currentUser = null;
                System.out.println("✅ Logged out successfully!");
                return; // Return to login screen
            }
            case 6 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice! Please try again.");
        }
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter stock: ");
        int stock = scanner.nextInt();

        Product product = new Product(0, name, category, price, stock);
        productService.addProduct(product);

        System.out.println("✅ Product added successfully!");
    }

    private void viewProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println("\n--- Product List ---");
        for (Product p : products) {
            System.out.println("ID: " + p.getId() + " | " + p.getName() + " | Price: " + p.getPrice());
        }
    }

    // User-specific methods
    private void viewMyOrders() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println("\n--- My Orders ---");
        for (Order o : orders) {
            if (o.getCustomerId() == currentUser.getId()) {
                System.out.println("Order ID: " + o.getId() + " | Customer ID: " + o.getCustomerId());
                for (OrderItem item : o.getItems()) {
                    System.out.println("   -> Product ID: " + item.getProductId() + " | Qty: " + item.getQuantity());
                }
            }
        }
    }
    
    private void updateMyProfile() {
        System.out.println("Current profile: " + currentUser);
        
        System.out.print("Enter new username (or press Enter to keep current): ");
        String username = scanner.nextLine();
        if (!username.isEmpty()) currentUser.setUsername(username);
        
        System.out.print("Enter new password (or press Enter to keep current): ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) currentUser.setPassword(password);
        
        userService.updateUser(currentUser);
        System.out.println("✅ Profile updated successfully!");
    }

    private void placeOrder() {
        Order order = new Order();
        order.setCustomerId(currentUser.getId());

        while (true) {
            System.out.print("Enter product ID (or 0 to finish): ");
            int productId = scanner.nextInt();
            if (productId == 0) break;

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();

            OrderItem item = new OrderItem(0, productId, qty);
            order.getItems().add(item);
        }

        orderService.addOrder(order);

        System.out.println("✅ Order placed successfully!");
    }

    private void viewOrders() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println("\n--- Orders ---");
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getId() + " | Customer ID: " + o.getCustomerId());
            for (OrderItem item : o.getItems()) {
                System.out.println("   -> Product ID: " + item.getProductId() + " | Qty: " + item.getQuantity());
            }
        }
    }

    // Product CRUD operations
    private void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Product product = productService.getProductById(id);
            System.out.println("Current product: " + product);
            
            System.out.print("Enter new name (or press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) product.setName(name);
            
            System.out.print("Enter new category (or press Enter to keep current): ");
            String category = scanner.nextLine();
            if (!category.isEmpty()) product.setCategory(category);
            
            System.out.print("Enter new price (or 0 to keep current): ");
            double price = scanner.nextDouble();
            if (price > 0) product.setPrice(price);
            
            System.out.print("Enter new stock (or -1 to keep current): ");
            int stock = scanner.nextInt();
            if (stock >= 0) product.setStock(stock);
            
            productService.updateProduct(product);
            System.out.println("✅ Product updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error updating product: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Product product = productService.getProductById(id);
            System.out.println("Product to delete: " + product);
            System.out.print("Are you sure? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                productService.deleteProduct(id);
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error deleting product: " + e.getMessage());
        }
    }

    // User CRUD operations
    private void viewUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("\n--- User List ---");
        for (User u : users) {
            System.out.println("ID: " + u.getId() + " | Username: " + u.getUsername() + " | Role: " + u.getRole());
        }
    }

    private void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            User user = userService.getUserById(id);
            System.out.println("Current user: " + user);
            
            System.out.print("Enter new username (or press Enter to keep current): ");
            String username = scanner.nextLine();
            if (!username.isEmpty()) user.setUsername(username);
            
            System.out.print("Enter new password (or press Enter to keep current): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) user.setPassword(password);
            
            System.out.print("Enter new role (ADMIN/USER or press Enter to keep current): ");
            String role = scanner.nextLine();
            if (!role.isEmpty()) user.setRole(role.toUpperCase());
            
            userService.updateUser(user);
            System.out.println("✅ User updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error updating user: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            User user = userService.getUserById(id);
            System.out.println("User to delete: " + user);
            System.out.print("Are you sure? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                userService.deleteUser(id);
                System.out.println("✅ User deleted successfully!");
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error deleting user: " + e.getMessage());
        }
    }

    // Order CRUD operations
    private void updateOrder() {
        System.out.print("Enter order ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Order order = orderService.getOrderById(id);
            System.out.println("Current order: " + order);
            
            System.out.print("Enter new customer ID (or 0 to keep current): ");
            int customerId = scanner.nextInt();
            if (customerId > 0) order.setCustomerId(customerId);
            
            orderService.updateOrder(order);
            System.out.println("✅ Order updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error updating order: " + e.getMessage());
        }
    }

    private void deleteOrder() {
        System.out.print("Enter order ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Order order = orderService.getOrderById(id);
            System.out.println("Order to delete: " + order);
            System.out.print("Are you sure? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                orderService.deleteOrder(id);
                System.out.println("✅ Order deleted successfully!");
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error deleting order: " + e.getMessage());
        }
    }
}
