package com.digitalmedia.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApiApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApiApplication.class);


  public static void main(String[] args) {
    SpringApplication.run(GatewayApiApplication.class, args);
  }
  @GetMapping(value = "/token")
  public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
    return Mono.just(authorizedClient.getAccessToken().getTokenValue());
  }

  @GetMapping("/")
  public Mono<String> index(WebSession session) {
    return Mono.just(session.getId());
  }

}
