/*
 */
package com.airhacks.ping;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
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
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String propertyName : new TreeSet<>(propertyNames)) {
            retVal.append(propertyName).append(" -> ").append(properties.getProperty(propertyName));
            retVal.append("<br>");
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
            retVal.append("<br>");
        }
        return retVal.toString();
    }

}
