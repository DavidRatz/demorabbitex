package be.technifutur.apigatewayv2.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.technifutur.apigatewayv2.predicate.NumberOfParamsRoutePredicateFactory;

//@Configuration
public class RouteLocatorConfig {
    //@Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, NumberOfParamsRoutePredicateFactory pf){
        return builder.routes()
            .route("to-client", 
                r-> r.path("/client/**")
                        .and()
                        .method("GET")
                        .filters(f -> f.rewritePath("/client/(?<path>.*)", "/$\\{path}")
                                        .addRequestParameter("mon-param", "sa-valeur"))
                        .uri("lb://client-service")
            )
            .route("to-film", 
            r-> r.path("/film/**")
                    .filters(f -> f.rewritePath("/film/(?<path>.*)", "/$\\{path}"))
                    .uri("lb://film-service") 
            )    
            .route("mot-film", 
            r-> r.path("/mot")
                    .and()
                    .method("GET")
                    .and()
                    .predicate(pf.apply(new NumberOfParamsRoutePredicateFactory.Config(0)))
                    .filters(f -> f.addRequestParameter("mot", "gateway"))
                    .uri("lb://film-service") 
            )    
            .build();

    }
}
