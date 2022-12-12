package me.elyor.springboottestcontainers;

import me.elyor.springboottestcontainers.extensions.TestContainersSetup;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestContainersSetup
class ApplicationIntegrationTests {

    @Test
    void contextLoads() {
    }

}
