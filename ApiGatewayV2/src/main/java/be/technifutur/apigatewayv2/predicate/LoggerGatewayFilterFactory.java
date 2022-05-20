package be.technifutur.apigatewayv2.predicate;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import be.technifutur.apigatewayv2.predicate.NumberOfParamsRoutePredicateFactory.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggerGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggerGatewayFilterFactory.Config> {

    public LoggerGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info(exchange.getRequest().getMethod() + " - " + exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    public static class Config {
        
    }
    
}
