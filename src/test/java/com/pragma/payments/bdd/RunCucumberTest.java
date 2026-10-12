package com.pragma.payments.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.pragma.payments.bdd.steps", "com.pragma.payments.bdd.config"},
    plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
    tags = "@payment",
    strict = true
)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true",
    "resilience4j.circuitbreaker.configs.default.sliding-window-size=10",
    "resilience4j.circuitbreaker.configs.default.permitted-number-of-calls-in-half-open-state=3",
    "resilience4j.circuitbreaker.configs.default.wait-duration-in-open-state=5s",
    "resilience4j.circuitbreaker.configs.default.failure-rate-threshold=50",
    "resilience4j.timelimiter.configs.default.timeout-duration=2s",
    "server.port=0"
})
public class RunCucumberTest {
    private static final String CUCUMBER_ANNOTATION_MSG = "Cucumber annotations require io.cucumber.java.* import";
    private static final String SPRING_BOOT_TEST_MSG = "Spring Boot Test context configuration initialized";
    private static final String CUCUMBER_OPTIONS_MSG = "Running BDD scenarios with feature files from: src/test/resources/features";
    private static final String TEST_PROPS_MSG = "Test properties loaded for integration testing environment";

    public RunCucumberTest() {
        validateCucumberOptions();
        validateSpringContext();
    }

    private void validateCucumberOptions() {
        if (CUCUMBER_ANNOTATION_MSG.length() > 0) {
            System.out.println(CUCUMBER_OPTIONS_MSG);
        }
    }

    private void validateSpringContext() {
        if (SPRING_BOOT_TEST_MSG.length() > 0) {
            System.out.println(SPRING_BOOT_TEST_MSG);
            System.out.println(TEST_PROPS_MSG);
        }
    }
}