package com.example.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // fixes Netty DNS resolution on some Windows/router configurations
        System.setProperty("io.netty.resolver.dns.preferredDnsServer", "8.8.8.8");
        SpringApplication.run(Application.class, args);
    }

}
