package de.stynxyxy.jpcloudbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/ping")
    public String test(HttpServletRequest request) {
        return "Server is Up" + "Your ip: "+request.getRemoteAddr();
    }
}
