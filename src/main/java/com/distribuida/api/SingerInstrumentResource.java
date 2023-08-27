package com.distribuida.api;

import com.distribuida.db.SingerInstrument;
import com.distribuida.dto.InstrumentDto;
import com.distribuida.dto.SingerDto;
import com.distribuida.dto.SingerInstrumentDto;
import com.distribuida.service.SingerInstrumentService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/singers-instruments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SingerInstrumentResource {

    @Inject
    private SingerInstrumentService serv;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response getAllSingersInstruments() {
        List<SingerInstrumentDto> list = serv.getAllSingersInstruments();
        return Response.ok(list).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getSingerInstrumentById(@PathParam("id") Integer id) {
        var si = serv.getSingerInstrumentById(id);
        if (si != null) {
            return Response.ok(si).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response createSingerInstrument(SingerInstrument si) {
        serv.createSingerInstrument(si);
        return Response.status(Response.Status.CREATED.getStatusCode(), "sing-instr created").build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response updateSingerInstrument(@PathParam("id") Integer id, SingerInstrument si) {
        si.setId(id);
        serv.updateSingerInstrument(si);
        return Response.ok().build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response deleteSingerInstrument(@PathParam("id") Integer id) {
        serv.deleteSingerInstrument(id);
        return Response.noContent().build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/singerId/{id}")
    public Response findInstrumentsBySingerId(@PathParam("id") Integer id) {
        List<InstrumentDto> instruments = serv.findBySingerId(id);
        return Response.ok(instruments).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/instrumentId/{id}")
    public Response findSingersByInstrumentId(@PathParam("id") Integer id) {
        List<SingerDto> singers = serv.findByInstrumentId(id);
        return Response.ok(singers).build();
    }

}
