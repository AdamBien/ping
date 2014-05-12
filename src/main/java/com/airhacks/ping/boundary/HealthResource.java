package com.airhacks.ping.boundary;

import com.airhacks.ping.control.ServerWatch;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author airhacks.com
 */
@Path("health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

    @Inject
    ServerWatch watch;

    @GET
    @Path("/boot-time")
    @Produces(MediaType.TEXT_PLAIN)
    public String bootTime() {
        return this.watch.getDateTime().toString();
    }
}
