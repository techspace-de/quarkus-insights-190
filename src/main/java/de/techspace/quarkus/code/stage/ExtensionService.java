package de.techspace.quarkus.code.stage;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Set;

@Path("/extensions")
@RegisterRestClient(configKey = "extensions-api")
interface ExtensionService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

}
