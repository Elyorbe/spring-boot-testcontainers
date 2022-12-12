package me.elyor.springboottestcontainers.customer.v1;

import me.elyor.springboottestcontainers.customer.CustomerRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerIntegrationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DataSource dataSource;

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

    @Test
    void whenCountInitialCustomersThenReturn2() {
        Assertions.assertEquals(customerRepository.count(), 2);
    }

    private static void runMigrations() {
        Flyway flyway = Flyway.configure()
                .dataSource(container.getJdbcUrl(), container.getUsername(),
                        container.getPassword())
                .load();
        flyway.migrate();
    }

}
