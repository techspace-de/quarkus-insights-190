package de.techspace.quarkus.code.stage;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@ConnectWireMock
class ExtensionResourceTest {

    WireMock wiremock;

    @ParameterizedTest
    @CsvSource({"io.quarkus:quarkus-rest,REST", "io.quarkus:quarkus-rest-client,REST Client"})
    void testExtensionsIdEndpoint(String id, String name) {
        given().pathParam("id", id).when().get("/extensions/{id}").then().statusCode(200)
                .body("id", is(id), "name", is(name));
    }

    @Test
    void testResponseTransformer() {
        given().pathParam("id", "invalid").when().get("/extensions/{id}").then().statusCode(200)
                .body("id", is("replaced"));
    }

    @Test
    void testStubWireMockExtension() {
        var extId = "io.quarkiverse.wiremock:quarkus-wiremock";
        wiremock.register(get(urlPathEqualTo("/extensions")).withQueryParam("id", equalTo(extId)).willReturn(
                aResponse().withStatus(200).withBody(
                        "[{\"id\":\"%s\",\"name\":\"Quarkus WireMock\",\"shortName\":\"wiremock\",\"keywords\":[\"any\"]}]".formatted(
                                extId)).withHeader("Content-Type", "application/json")));

        given().pathParam("id", extId).when().get("/extensions/{id}").then().statusCode(200).body("id", is(extId));
    }

}
