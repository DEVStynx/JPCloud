package de.stynxyxy.jpcloudbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static String sourcePath = "src\\main\\resources\\cloud";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}