package com.example.keycloack;

import org.keycloak.adapters.springboot.KeycloakBaseSpringBootConfiguration;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

//@Component
public class KeycloakUMAConfigResolver extends KeycloakSpringBootConfigResolver {

    @Autowired
    private KeycloakBaseSpringBootConfiguration cfg;

    public KeycloakUMAConfigResolver() throws Exception {
        Field f = KeycloakSpringBootConfigResolver.class.getDeclaredField("adapterConfig");
        f.setAccessible(true);
        KeycloakSpringBootProperties properties = (KeycloakSpringBootProperties) f.get(null);

        properties.getPolicyEnforcerConfig().setUserManagedAccess(new PolicyEnforcerConfig.UserManagedAccessConfig());
    }


}
