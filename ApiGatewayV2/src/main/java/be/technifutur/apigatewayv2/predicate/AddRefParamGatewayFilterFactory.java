package be.technifutur.apigatewayv2.predicate;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AddRefParamGatewayFilterFactory extends AbstractGatewayFilterFactory<AddRefParamGatewayFilterFactory.Config> {

    public AddRefParamGatewayFilterFactory() {
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
