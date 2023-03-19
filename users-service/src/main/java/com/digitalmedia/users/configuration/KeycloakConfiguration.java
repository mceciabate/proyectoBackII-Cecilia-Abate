package com.digitalmedia.users.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
@Setter
public class KeycloakConfiguration {
//    @Value("http://localhost:8085/")
//    public String serverUrl;
//    @Value("DH")
//    public String realm;
//    @Value("admin-cli")
//    public String clientId;
//    @Value("QuhE014lLH9y66Mlxb9aa9PzO3OZ1sRO")
//    public String clientSecret;

//
//    public KeycloakConfiguration() {
//    }
//
//    @Bean
//    public Keycloak getInstance() {
//        return     KeycloakBuilder.builder()
//                .realm(realm)
//                .serverUrl(serverUrl)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//                .build();
//
//            }
//}

    //    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(
//            String username,
//            String password) {
//        return KeycloakBuilder.builder()
//                .realm(realm)
//                .serverUrl(serverUrl)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .username(username)
//                .password(password);
//    }
//
//    public JsonNode refreshToken(String refreshToken) throws UnirestException {
//        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";
//        return Unirest.post(url)
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .field("client_id", clientId)
//                .field("client_secret", clientSecret)
//                .field("refresh_token", refreshToken)
//                .field("grant_type", "refresh_token")
//                .asJson().getBody();
//    }
//}
//@Configuration
//public class KeycloakConfiguration {
//
    @Value("http://localhost:8085/")
    private String serverUrl;
    @Value("DH")
    private String realm;
    @Value("usuarios")
    private String clientId;
    @Value("8YnE9JfYvkSEiwiogZZFxvs02LjTTnyH")
    private String clientSecret;

    @Bean
    public Keycloak buildClient() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
//}

