package com.mentor.triptrekker.auditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
public class AuditingApp {
    public static void main(String[] args) {
        SpringApplication.run(AuditingApp.class, args);
    }
}
