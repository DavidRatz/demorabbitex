package be.technifutur.reservation.config;

import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import be.technifutur.reservation.models.entities.Facture;
import be.technifutur.reservation.services.ReservationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitConfig {

    
    @Autowired
    private ReservationService service;

    //@Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
    
    // @Bean
    // public ObjectMapper objectMapper(){
    //     return new ObjectMapper().registerModule(new JavaTimeModule());
    // }

    //@Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    //@Bean("facture_queue")
    public Queue factureQueue(){
        return new Queue("facture_queue", true);
    }

    //@Bean("reservation_queue")
    public Queue ReservationQueue(){
        return new Queue("reservation_queue");
    }

    // @Bean
    // public FanoutExchange fanoutExchange(){
    //     return new FanoutExchange("fanout.facture");
    // }

    // @Bean
    // public TopicExchange topicExchange(){
    //     return new TopicExchange("topic.facture");
    // }

    //@Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct.messages");
    }

    //@Bean
    public Binding fBind(DirectExchange exchange, @Qualifier("facture_queue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("facture");
    }

    // @Bean
    // public Binding cBind(DirectExchange exchange, @Qualifier("reservation_queue") Queue queue){
    //     return BindingBuilder.bind(queue).to(exchange).with("reservation");
    // }

    @Bean
    public Consumer<Facture> reccevoirfacture(){
        return (f) -> {
            log.info("RECIEVED FACTURE: " + f);
            service.setToFacture(f.getReserv_ref());

            log.info("RESERVATION FACTURE");
        };
    }
}
