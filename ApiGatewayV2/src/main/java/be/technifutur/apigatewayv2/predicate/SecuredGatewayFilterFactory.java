package be.technifutur.apigatewayv2.predicate;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import be.technifutur.apigatewayv2.service.AuthenticationService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SecuredGatewayFilterFactory extends AbstractGatewayFilterFactory<SecuredGatewayFilterFactory.Config> {

    private final AuthenticationService authService;

    public SecuredGatewayFilterFactory(AuthenticationService authService) {
        super(Config.class);
        this.authService = authService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain) -> {
            List<String> authHeader = exchange.getRequest()
                    .getHeaders()
                    .get("Authorization");

            if( authHeader != null && authHeader.size() > 0 )
                return authService.validateToken(authHeader.get(0))
                        .flatMap(resp -> {
                            if( resp.statusCode().is2xxSuccessful() )
                                return chain.filter(exchange);
                            return buildResponse(exchange);
                        });
            return buildResponse(exchange);

        };
    }

    private Mono<Void> buildResponse(ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

//    private Mono<Void> validate(
//            ServerWebExchange exchange,
//            Config config,
//            UserDTO user,
//            GatewayFilterChain chain
//    ){
//
//        boolean authValid = true;
//        if( config.anyRoles.size() != 0 || config.allRoles.size() != 0 ) {
//            authValid = user != null
//                    && config.anyRoles.stream()
//                    .anyMatch(role -> user.getRoles().contains(role))
//                    && user.getRoles().containsAll(config.allRoles);
//        }
//
//        if( !authValid ) {
//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
//
//        return chain.filter(exchange);
//    }
//
//    private boolean allMatch(List<String> actualRoles, List<String> expectedRoles){
//        return actualRoles.containsAll(expectedRoles);
//    }

    @Getter
    @Setter
    public static class Config{
        private List<String> anyRoles = List.of();
        private List<String> allRoles = List.of();
    }
}