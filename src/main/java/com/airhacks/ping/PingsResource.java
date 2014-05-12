/*
 */
package com.airhacks.ping;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author adam-bien.com
 */
@Path("pings")
@Produces(MediaType.APPLICATION_JSON)
public class PingsResource {

    @GET
    @Path("/echo/{echo}")
    public String echo(@PathParam("echo") String param) {
        return param;
    }

    @GET
    @Path("/system-properties")
    public JsonObject systemProperties() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        entries.stream().forEach(e -> builder.add(String.valueOf(e.getKey()), String.valueOf(e.getValue())));
        return builder.build();
    }

    @GET
    @Path("/jndi/{namespace}")
    public JsonObject jndi(@PathParam("namespace") String namespace) throws NamingException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        InitialContext c = new InitialContext();
        NamingEnumeration<NameClassPair> list = c.list(namespace);
        while (list.hasMoreElements()) {
            NameClassPair nameClassPair = list.nextElement();
            String name = nameClassPair.getName();
            String type = nameClassPair.getClassName();
            builder.add(name, type);
        }
        return builder.build();
    }

}
