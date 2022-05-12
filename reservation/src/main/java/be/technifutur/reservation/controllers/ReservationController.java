package be.technifutur.reservation.controllers;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import be.technifutur.reservation.models.dtos.ReservationDTO;
import be.technifutur.reservation.models.entities.Reservation;
import be.technifutur.reservation.models.forms.ReservForm;
import be.technifutur.reservation.rabbit.MessageSender;
import be.technifutur.reservation.services.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@RequestBody ReservForm reservation) throws JsonProcessingException{
        service.create(reservation.map());
    }

    @GetMapping("/facture")
    public ResponseEntity<List<Reservation>> getReservationFacture(){
        return ResponseEntity.ok(service.getResvFactures());
    }

    @GetMapping("/ref")
    public ResponseEntity<ReservationDTO> getReservationAndFacture(@RequestParam UUID ref) throws Exception{
        return ResponseEntity.ok(service.getReservationPriceFromFacture(ref));
    }


}
