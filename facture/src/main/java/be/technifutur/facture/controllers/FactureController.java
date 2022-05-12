package be.technifutur.facture.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.technifutur.facture.models.Facture;
import be.technifutur.facture.services.FactureService;

@RestController
@RequestMapping("/facture")
public class FactureController {
    @Autowired
    private FactureService service;

    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures(){
        return ResponseEntity.ok(service.getFactures());
    }

    @GetMapping("/ref")
    public ResponseEntity<Facture> getAllFactures(@RequestParam UUID reserv_ref){
        return ResponseEntity.ok(service.getFactureByRef(reserv_ref));
    }
}
