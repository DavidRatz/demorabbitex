package be.technifutur.controllers;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    @Autowired
    private RestTemplate template;

    private final String url = "http://localhost:8082/reservation/";
    private final String urlFacture = "http://localhost:8081/facture/";

    @PostMapping
    public ResponseEntity<?> askForReserv(@RequestBody Map<String, String> request){
        System.out.println(request);

        ResponseEntity<Object> response = template.postForEntity(url + "add", request, Object.class);

        return response;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getAllReservFacture(){
        ResponseEntity<?> response = template.getForEntity(url + "facture", List.class);

        return response;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/ref")
    public ResponseEntity<?> getReservAndFacture(@RequestParam Object request){
        ResponseEntity<Object> reservFact =  template.getForEntity(url + "ref?ref="+request, Object.class);

        return reservFact;

    }

}
