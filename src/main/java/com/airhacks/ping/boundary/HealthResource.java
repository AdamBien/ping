package com.airhacks.ping.boundary;

import com.airhacks.ping.control.ServerWatch;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
    @Path("/start-time")
    @Produces(MediaType.TEXT_PLAIN)
    public String bootTime() {
        return this.watch.getDateTime().toString();
    }

    @GET
    @Path("/uptime")
    @Produces(MediaType.TEXT_PLAIN)
    public String uptime() {
        return Long.toString(ChronoUnit.MILLIS.between(this.watch.getDateTime(), ZonedDateTime.now()));
    }
    
    @GET
    @Path("/current-memory")
    public JsonObject availableHeap() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("Available memory in mb", this.watch.availableMemoryInMB()).
                add("Used memory in mb", this.watch.usedMemoryInMb()).
                add("Memory at start time", this.watch.usedMemoryInMbAtStartTime());
        return builder.build();
    }

    @GET
    @Path("/os-info")
    public JsonObject osInfo() {
        return this.watch.osInfo();
    }

}
