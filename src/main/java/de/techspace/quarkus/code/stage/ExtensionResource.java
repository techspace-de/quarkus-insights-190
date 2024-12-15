package de.techspace.quarkus.code.stage;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Path("/extensions")
public class ExtensionResource {

    @RestClient
    ExtensionService extensionService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Extension id(@PathParam("id") String id) {
        return extensionService.getById(id).stream().findFirst().orElseThrow(NotFoundException::new);
    }

}

