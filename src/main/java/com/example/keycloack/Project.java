package com.example.keycloack;

import java.util.UUID;

public final class Project {

    private final UUID id;
    private final String name;
    private final String region;
    private final String owner;

    public Project(UUID id, String name, String region, String owner) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getOwner() {
        return owner;
    }
}
