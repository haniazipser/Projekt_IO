package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Services.KeycloakClientService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
public class KeycloakController {
    private final KeycloakClientService keycloakClientService;
    @GetMapping("/user-info/{email}")
    public Mono<String> getUserInfo(@PathVariable String email) {
        return keycloakClientService.getUserByEmail(email);
    }
}
