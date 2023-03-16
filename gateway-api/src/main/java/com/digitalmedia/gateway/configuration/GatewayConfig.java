package com.digitalmedia.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("msmovies", r -> r.path("/movies/**").filters(f -> f.filter(filterFactory.apply())).uri("lb://api-movies"))
                .route("msusers", r -> r.path("/users/**").filters(f -> f.filter(filterFactory.apply())).uri("lb://users-service"))
                .build();
    }
}
