package me.elyor.springboottestcontainers.extensions;

import me.elyor.springboottestcontainers.testcontainers.AppMariaDBContainer;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MariaDBSetupExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        AppMariaDBContainer.container.start();
        updateDataSourceProps(AppMariaDBContainer.container);
        runMigrations(AppMariaDBContainer.container);
    }

    private void updateDataSourceProps(AppMariaDBContainer container) {
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }

    private void runMigrations(AppMariaDBContainer container) {
        Flyway flyway = Flyway.configure()
                .dataSource(container.getJdbcUrl(), container.getUsername(),
                        container.getPassword())
                .load();
        flyway.migrate();
    }

}
