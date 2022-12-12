package me.elyor.springboottestcontainers.customer.v3;

import me.elyor.springboottestcontainers.customer.CustomerRepository;
import me.elyor.springboottestcontainers.extensions.MariaDBSetupExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MariaDBSetupExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerIntegrationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void whenCountInitialCustomersThenReturn2() {
        Assertions.assertEquals(customerRepository.count(), 2);
    }

}
