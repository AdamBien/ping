/*
 */
package com.airhacks.ping;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author adam-bien.com
 */
@Path("pings")
public class PingsResource {

    @GET
    @Path("/echo/{echo}")
    public String echo(@PathParam("echo") String param) {
        return param;
    }

    @GET
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

    @GET
    @Path("/jndi/{namespace}")
    public String jndi(@PathParam("namespace") String namespace) throws NamingException {
        StringBuilder retVal = new StringBuilder();
        InitialContext c = new InitialContext();
        NamingEnumeration<NameClassPair> list = c.list(namespace);
        while (list.hasMoreElements()) {
            NameClassPair nameClassPair = list.nextElement();
            String name = nameClassPair.getName();
            String type = nameClassPair.getClassName();
            retVal.append(name).append(" -> ").append(type);
            retVal.append(File.separatorChar);
        }
        return retVal.toString();
    }

}
