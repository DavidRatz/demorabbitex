package be.technifutur.apigatewayv2.predicate;

import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class NumberOfParamsRoutePredicateFactory extends AbstractRoutePredicateFactory<NumberOfParamsRoutePredicateFactory.Config>{

    public NumberOfParamsRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (exchange) -> {
            int numberOfParams = exchange.getRequest().getQueryParams().size();
            if(config.equals != null)
                return config.equals == numberOfParams;
            if(config.min != null && config.max != null){
                return config.min < numberOfParams && numberOfParams < config.max;
            }
            else if(config.min != null ){
                return config.min < numberOfParams;
            }
            else if(config.max != null ){
                return config.max > numberOfParams;
            }
            
            return true;

        };
    }

    public static class Config{
        private Integer equals;
        private Integer min;
        private Integer max;

        public Config() {
            
        }

        public Config(Integer equals) {
            this.equals = equals;
        }

        public Config(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        public Integer getEquals() {
            return equals;
        }

        public void setEquals(Integer equals) {
            this.equals = equals;
        }

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        
        
    }

}