package com.ecommerce.ecommerce_springboot.controller;

import com.ecommerce.ecommerce_springboot.model.Product;
import com.ecommerce.ecommerce_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleController {

    @Autowired
    private ProductService productService;

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n==== E-Commerce Menu ====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProducts();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter category: ");
        String category = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter stock: ");
        int stock = scanner.nextInt();

        Product product = new Product(0, name, category, price, stock);
        productService.addProduct(product);
        System.out.println("✅ Product added!");
    }

    private void viewProducts() {
        List<Product> products = productService.getAllProducts();
        products.forEach(System.out::println);
    }
}
