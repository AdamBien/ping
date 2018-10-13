/*
 */
package com.airhacks.ping.boundary;

import java.util.Map;
import java.util.Properties;
import static java.util.stream.Collectors.toMap;
import javax.json.Json;
import static javax.json.Json.createValue;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.stream.JsonCollectors;
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

        Properties properties = System.getProperties();
        Map<String, JsonValue> map = properties.entrySet().
                stream().
                collect(toMap(k -> k.toString(), v -> createValue(v.getValue().toString())));
        return map.entrySet().stream().collect(JsonCollectors.toJsonObject());
    }

    @GET
    @Path("/environment-variables")
    public JsonObject environmentVariables() {
        Map<String, JsonValue> environment
                = System.getenv().
                        entrySet().
                        stream().
                        collect(toMap(Map.Entry::getKey, v -> createValue(v.getValue())));
        return environment.entrySet().stream().collect(JsonCollectors.toJsonObject());
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
