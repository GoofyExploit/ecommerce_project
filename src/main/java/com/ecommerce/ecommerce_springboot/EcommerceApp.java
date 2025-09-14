package com.ecommerce.ecommerce_springboot;

import com.ecommerce.ecommerce_springboot.app.ConsoleApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EcommerceApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EcommerceApp.class, args);
        ConsoleApp app = context.getBean(ConsoleApp.class);
        app.start();
    }
}
