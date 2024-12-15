package de.techspace.quarkus.code.stage;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExtensionResourceTest {

    @ParameterizedTest
    @CsvSource({"io.quarkus:quarkus-rest,REST", "io.quarkus:quarkus-rest-client,REST Client"})
    void testExtensionsIdEndpoint(String id, String name) {
        given().pathParam("id", id).when().get("/extensions/{id}").then().statusCode(200)
                .body("id", is(id), "name", is(name));
    }

}
