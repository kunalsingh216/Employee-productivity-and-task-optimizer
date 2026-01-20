package com.example.productivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProductivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductivityApplication.class, args);
    }

    
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║     🎉 Server running at http://localhost:8080     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println("\n");
    }
}


