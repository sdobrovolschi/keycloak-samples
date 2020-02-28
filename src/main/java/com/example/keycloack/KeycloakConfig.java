package com.example.keycloack;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver(KeycloakSpringBootProperties properties) {
//        properties.getPolicyEnforcerConfig().setUserManagedAccess(new PolicyEnforcerConfig.UserManagedAccessConfig());
        return new KeycloakSpringBootConfigResolver();
    }
}
