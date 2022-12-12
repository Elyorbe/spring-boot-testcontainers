package me.elyor.springboottestcontainers.extensions;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

/**
 * Composed annotation to configure shared testcontainers instances
 * for integration tests.
 * Currently only MariaDB instance is configured.
 * Recommended to use for configuration of further testcontainers instances
 *
 * @author Elyorbek
 * */
@ExtendWith(MariaDBSetupExtension.class)
@Target({
        ElementType.TYPE, ElementType.METHOD,
        ElementType.FIELD, ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestContainersSetup {
}
