package me.elyor.springboottestcontainers.customer.v2;

import org.flywaydb.core.Flyway;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BaseIntegrationTests {

    @Container
    static MariaDBContainer container = new MariaDBContainer("mariadb:10.5")
            .withDatabaseName("customers")
            .withUsername("elyor")
            .withPassword("strong-password");


    @DynamicPropertySource
    static void overrideDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        runMigrations();
    }

    private static void runMigrations() {
        Flyway flyway = Flyway.configure()
                .dataSource(container.getJdbcUrl(), container.getUsername(),
                        container.getPassword())
                .load();
        flyway.migrate();
    }

}
