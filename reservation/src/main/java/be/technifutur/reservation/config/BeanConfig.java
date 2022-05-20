package be.technifutur.reservation.config;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import be.technifutur.sharedclass.config.JwtValidationFilter;

// @Slf4j
@Configuration
public class BeanConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public JwtValidationFilter jwtValidationFilter(){
        return new JwtValidationFilter(restTemplate());
    }
}