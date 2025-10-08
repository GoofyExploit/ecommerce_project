package com.ecommerce.ecommerce_springboot;

import com.ecommerce.ecommerce_springboot.app.ConsoleApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EcommerceApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EcommerceApp.class, args);
        
        // Only start console app if explicitly requested
        // This allows the microservices to run without console prompts
        if (args.length > 0 && args[0].equals("--console")) {
            ConsoleApp app = context.getBean(ConsoleApp.class);
            app.start();
        } else {
            System.out.println("E-Commerce API Server started successfully!");
            System.out.println("Console interface disabled. Use --console flag to enable.");
            System.out.println("Web frontend available at: http://localhost:3000");
        }
    }
}
