package be.technifutur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/facture")
public class FactureController {
    @Autowired
    private RestTemplate template;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getAllFactures(){
        ResponseEntity<?> response = template.getForEntity("http://localhost:8081/facture", List.class);

        return response;
    }
}
