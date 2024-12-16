package de.techspace.quarkus.code.stage;

import io.quarkiverse.wiremock.devservice.WireMockConfigKey;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

import java.util.Map;

@QuarkusIntegrationTest
@TestProfile(ExtensionResourceIT.WireMockTestProfile.class)
public class ExtensionResourceIT extends ExtensionResourceTest {

    public static class WireMockTestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            // https://docs.docker.com/desktop/features/networking/#use-cases-and-workarounds
            var hostname = Boolean.getBoolean("quarkus.container-image.build") ? "host.docker.internal" : "localhost";
            return Map.of("quarkus.rest-client.extensions-api.url",
                    "http://%s:${%s}".formatted(hostname, WireMockConfigKey.PORT));
        }
    }


}
