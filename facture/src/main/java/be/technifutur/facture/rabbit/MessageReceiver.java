package be.technifutur.facture.rabbit;

import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.technifutur.facture.*;
import be.technifutur.facture.models.Facture;
import be.technifutur.facture.models.Reservation;
import be.technifutur.facture.services.FactureService;

@Component
public class MessageReceiver {

    private Logger log = LoggerFactory.getLogger(MessageReceiver.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private FactureService service;

    @RabbitListener(queues = "reservation_queue")
    public void receiveReservation(String message) throws JsonProcessingException{
        // ObjectMapper mapper = new ObjectMapper();
        Reservation r = mapper.readValue(message, Reservation.class);
        log.info("Reservation RECEIVED - " + r);

        service.createFacture((int)r.getDate_arrive().until(r.getDate_depart(),ChronoUnit.DAYS), r.getRef());

    }
}
