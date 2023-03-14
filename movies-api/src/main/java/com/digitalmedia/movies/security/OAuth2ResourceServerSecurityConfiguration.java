package com.digitalmedia.movies.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
  }


//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(new KeyCloakJwtAuthenticationConverter());
//    http.cors().and().csrf().disable()
//
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and().authorizeRequests().anyRequest().authenticated();
//}

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withJwkSetUri("http://localhost:8085/realms/DH/protocol/openid-connect/certs").build();
  }
}
