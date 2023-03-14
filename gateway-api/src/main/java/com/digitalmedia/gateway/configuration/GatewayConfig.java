package com.digitalmedia.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class GatewayConfig {
    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("msmovies", r -> r.path("/api/v1/movies/**").filters(f -> f.filter(filterFactory.apply())).uri("lb://api-movies"))
                .route("msusers", r -> r.path("/api/v1/users/**").filters(f -> f.filter(filterFactory.apply())).uri("lb://api-users"))
                .build();
    }
}
/*	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
				.route("alert", r -> r.path("/alert/**").filters(f -> f.filter(filter)).uri("lb://alert"))
				.route("echo", r -> r.path("/echo/**").filters(f -> f.filter(filter)).uri("lb://echo"))
				.route("hello", r -> r.path("/hello/**").filters(f -> f.filter(filter)).uri("lb://hello")).build();
	}*/