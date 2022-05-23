package be.technifutur.facture.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitConfig {

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
        return new Queue("facture_queue");
    }

    //@Bean("reservation_queue")
    public Queue reservationQueue(){
        return new Queue("reservation_queue", true);
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

    // @Bean
    // public Binding fBind(DirectExchange exchange, @Qualifier("facture_queue") Queue queue){
    //     return BindingBuilder.bind(queue).to(exchange).with("facture");
    // }

    //@Bean
    public Binding rBind(DirectExchange exchange, @Qualifier("reservation_queue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("reservation");
    }
}
