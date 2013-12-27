/*
 */
package com.airhacks.ping;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author adam-bien.com
 */
@Path("pings")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class PingsResource {

    @Path("/echo/{echo}")
    public String echo(@PathParam("echo") String param) {
        return param;
    }

    @Path("/system-properties")
    public String systemProperties() {
        StringBuilder retVal = new StringBuilder();
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> property : entries) {
            retVal.append(property.getKey()).append(" -> ").append(property.getValue());
            retVal.append(File.separatorChar);
        }
        return retVal.toString();
    }

}
