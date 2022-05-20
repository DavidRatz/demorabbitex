package be.technifutur.reservation.services;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import be.technifutur.reservation.models.dtos.ReservationDTO;
import be.technifutur.reservation.models.entities.Facture;
import be.technifutur.reservation.models.entities.Reservation;
import be.technifutur.reservation.models.entities.Reservation.Status;
import be.technifutur.reservation.rabbit.MessageSender;

@Service
public class ReservationService {
    private final List<Reservation> list = new ArrayList<>();
    @Autowired
    private MessageSender sender;
    @Autowired
    private RestTemplate template;
    private final String urlFacture = "lb://facture-service/facture/";

    public void create(Reservation reservation){
        
        try {
            sender.sendReservationToFacture(reservation);
            list.add(reservation);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void setToFacture(UUID ref){
        // list.stream()
        //         .filter((e) -> e.getRef().equals(ref))
        //         .findFirst()
        //         .ifPresent((e) -> e.setStatus(Reservation.Status.FACTURE));
        Optional<Reservation> reservation = list.stream().filter(r -> r.getRef().equals(ref)).findFirst();

        if(reservation.isPresent()){
            reservation.get().setStatus(Status.FACTURE);
        }
    }

    public List<Reservation> getResvFactures(){
        return list.stream().filter(r -> r.getStatus() == Status.FACTURE).toList();
    }

    public ReservationDTO getReservationPriceFromFacture(UUID ref) throws Exception {
        Double price = 0D;
        ResponseEntity<Facture> f = template.getForEntity(urlFacture + "ref?reserv_ref=" + ref, Facture.class);
        if(f.getStatusCode() == HttpStatus.OK)
            price = f.getBody().getPrix();
        else
            throw new Exception("No facture found");
        
        Reservation reservation = list.stream().filter(r -> r.getRef().equals(ref)).findFirst().orElseThrow(() -> new Exception("No reservation found"));

        return ReservationDTO.of(reservation, price);
    }
}
