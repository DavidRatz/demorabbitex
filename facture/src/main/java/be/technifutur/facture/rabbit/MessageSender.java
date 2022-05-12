package be.technifutur.facture.rabbit;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.technifutur.facture.*;
import be.technifutur.facture.models.Facture;

@Component
public class MessageSender implements InitializingBean{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper mapper;

    public void sendFactureToReservation(Facture facture)
    {
        // Message message = MessageBuilder.withBody(json.getBytes()).setContentType("application/json").build();
        
        // rabbitTemplate.send("topic.facture", "facture.compta",message);

        String json;
        try {
            json = mapper.writeValueAsString(facture);
            rabbitTemplate.convertAndSend("direct.messages", "facture",json);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Facture f = new Facture("Luc Dubois", 10, "l'adresse", List.of(new Facture.Produit("Carotte",25d)));

        // ObjectMapper mapper = new ObjectMapper();
        // String fJson = mapper.writeValueAsString(f);

        // sendFactureToCompta(fJson);
        
    }
    
}
