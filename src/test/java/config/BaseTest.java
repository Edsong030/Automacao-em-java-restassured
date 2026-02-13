package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static RequestSpecification requestSpecification;

    @BeforeAll
    public static void setup() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://serverest.dev")
                .setContentType("application/json")
                .build();
    }
}
