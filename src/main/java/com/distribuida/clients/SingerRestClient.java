package com.distribuida.clients;

import com.distribuida.dto.SingerDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/singers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "singerRestClient")
public interface SingerRestClient {

    @GET
    List<SingerDto> getAllSingers();

    @GET
    @Path("/{id}")
    SingerDto getSingerById(@PathParam("id") Integer id);


}
