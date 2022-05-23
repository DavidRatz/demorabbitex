package be.technifutur.facture.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.technifutur.facture.models.Facture;
import be.technifutur.facture.rabbit.MessageSender;

@Service
public class FactureService {
    private final List<Facture> list = new ArrayList<>();
    // @Autowired
    // private MessageSender sender;

    public Facture createFacture(int nbrNuit, UUID reserv_ref){
        double priceByNight = 50.0d;
        double totalPrice = priceByNight*nbrNuit;
        Facture facture = new Facture(totalPrice, reserv_ref);
        list.add(facture);

        //sender.sendFactureToReservation(facture);
        return facture;
    }

    public List<Facture> getFactures(){
        return list;
    }

    public Facture getFactureByRef(UUID reserv_ref) {
        return list.stream().filter(r -> r.getReserv_ref().equals(reserv_ref)).findFirst().orElseThrow();
    }
}
