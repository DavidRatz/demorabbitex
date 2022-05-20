package be.technifutur.apigatewayv2.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class AuthenticationService {

    private final WebClient webClient;
    //private final RestTemplate restTemplate;

    public AuthenticationService(WebClient.Builder builder/*, RestTemplate restTemplate*/) {
        this.webClient = builder.baseUrl("lb://user-service").build();
        //this.restTemplate = restTemplate;
    }

    public Mono<ClientResponse> validateToken(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

//        return restTemplate.exchange("http://user-service/login", HttpMethod.POST,entity, UserDTO.class)
//                .getBody();

        return webClient.get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/login")
                                .build()
                )
                .header("Authorization", token)
                .exchangeToMono( Mono::just );
    }

}
