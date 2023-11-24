package com.modsen.integration;


import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class BasePostgres {

    protected static final GenericContainer postgresContainer;

    static {
        postgresContainer = new GenericContainer(DockerImageName.parse("postgres:15-alpine"))
                .withExposedPorts(5432);
        postgresContainer.addEnv("POSTGRES_DB", "book_db");
        postgresContainer.addEnv("POSTGRES_USERNAME", "postgres");
        postgresContainer.addEnv("POSTGRES_PASSWORD", "postgres");
        postgresContainer.start();
    }
}
