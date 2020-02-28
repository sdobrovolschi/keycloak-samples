package com.example.keycloack;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(path = "/projects", produces = APPLICATION_JSON_UTF8_VALUE)
class ProjectResource {

    private final Map<UUID, Project> projects = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        Arrays.asList(new Project(UUID.randomUUID(), "Project X", "us-east", "alice"),
                new Project(UUID.randomUUID(), "Project Y", "us-west", "susan"))
                .forEach(project -> projects.put(project.getId(), project));
    }

    @GetMapping
    Collection<Project> list(KeycloakPrincipal<KeycloakSecurityContext> principal) {
        //1. managers have access to their regions only
        //2. agents have access to their projects only
        return projects.values();
    }

    @GetMapping(path = "/{projectId}")
    Project get(@PathVariable("projectId") UUID projectId) {
        return projects.get(projectId);
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    void add(@RequestBody CreateProjectRequest request, KeycloakPrincipal<KeycloakSecurityContext> principal) {
        String region = (String) principal.getKeycloakSecurityContext().getToken().getOtherClaims().get("region");
        String owner = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Project project = new Project(UUID.randomUUID(), request.name, region, owner);
        projects.put(project.getId(), project);
    }

    private static final class CreateProjectRequest {

        private final String name;

        @JsonCreator
        private CreateProjectRequest(@JsonProperty("name") String name) {
            this.name = name;
        }
    }
}
