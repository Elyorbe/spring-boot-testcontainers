package me.elyor.springboottestcontainers.testcontainers;

import org.testcontainers.containers.MariaDBContainer;

public class AppMariaDBContainer extends MariaDBContainer<AppMariaDBContainer> {

    private static final String IMAGE_VERSION = "mariadb:10.5";
    private static final String DATABASE_NAME = "customers";
    private static final String USERNAME = "elyor";
    private static final String PASSWORD = "strong-password";

    public static AppMariaDBContainer container = new AppMariaDBContainer()
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD);

    public AppMariaDBContainer() {
        super(IMAGE_VERSION);
    }

}
