package com.digitalmedia.gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration

public class SecurityConfiguration {



    private final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity http) {

        http
                .authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login(withDefaults()); // to redirect to oauth2 login page.
                 http.csrf().disable()
                .logout()
                .logoutSuccessHandler(oidcServerLogoutSuccessHandler());


        return http.build();
    }


    private ServerLogoutSuccessHandler oidcServerLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcClientInitiatedServerLogoutSuccessHandler = new OidcClientInitiatedServerLogoutSuccessHandler(reactiveClientRegistrationRepository);
        oidcClientInitiatedServerLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/login");
        return oidcClientInitiatedServerLogoutSuccessHandler;
    }

}