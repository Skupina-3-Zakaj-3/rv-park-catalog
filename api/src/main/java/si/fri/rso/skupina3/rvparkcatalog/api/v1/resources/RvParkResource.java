package si.fri.rso.skupina3.rvparkcatalog.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.skupina3.rvparkcatalog.lib.RvPark;
import si.fri.rso.skupina3.rvparkcatalog.services.beans.RvParkBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/parks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class RvParkResource {

    private Logger log = Logger.getLogger(RvParkResource.class.getName());

    @Inject
    private RvParkBean rvParkBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getParks() {

        log.info("getParks() - GET");
        List<RvPark> parks = rvParkBean.getRvParks(uriInfo);

        return Response.status(Response.Status.OK).entity(parks).build();
    }

    @GET
    @Path("{id}")
    public Response getRvPark(@PathParam("id") Integer id) {

        log.info("getRvPark() - GET");

        RvPark rvPark = rvParkBean.getRvPark(id);

        if(rvPark == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(rvPark).build();
    }

    @POST
    public Response createRvPark(RvPark rvPark) {

        log.info("createRvPark() - POST");

        // TODO: check for needed parameters and send back bad request if they are not present
        if (rvPark.getDescription() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            rvPark = rvParkBean.createRvPark(rvPark);
        }

        return Response.status(Response.Status.OK).entity(rvPark).build();

    }

    @PUT
    @Path("{rvParkId}")
    public Response putRv(@PathParam("rvParkId") Integer rvParkId, RvPark rvPark) {

        log.info("putRvPark() - PUT");

        rvPark = rvParkBean.updateRvPark(rvParkId, rvPark);

        if (rvPark == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(rvPark).build();

    }

    @DELETE
    @Path("{rvParkId}")
    public Response deleteRv(@PathParam("rvParkId") Integer rvParkId) {

        log.info("deleteRvPark() - DELETE");

        boolean deleted = rvParkBean.deleteRvPark(rvParkId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
