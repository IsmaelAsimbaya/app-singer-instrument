package com.distribuida.clients;

import com.distribuida.dto.InstrumentDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/instruments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "singerRestClient")
public interface InstrumentRestClient {

    @GET
    List<InstrumentDto> getAllInstrumentsDto();

    @GET
    @Path("/{id}")
    InstrumentDto getInstrumentDtoById(@PathParam("id") Integer id);
}
