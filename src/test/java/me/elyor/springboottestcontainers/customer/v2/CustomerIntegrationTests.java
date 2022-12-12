package me.elyor.springboottestcontainers.customer.v2;

import me.elyor.springboottestcontainers.customer.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerIntegrationTests extends BaseIntegrationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void whenCountInitialCustomersThenReturn2() {
        Assertions.assertEquals(customerRepository.count(), 2);
    }

}
