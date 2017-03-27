# Tiny Ping For JavaEE 7 Application Servers

Ping exposes basic health, JNDI, JVM and OS metrics via a convenient URI, 
which enables an easy integration into application monitoring solutions
and smoke tests. The whole WAR file is only a couple of kilobytes in size.

Tested with GlassFish, Payara, TomEE, WebSphere Liberty and Wildfly.

## Features:

  * Echo Resource `/resources/pings/echo/{message}`
  * List system properties `/resources/pings/system-properties`
  * List environment variables `/resources/pings/environment-variables` 
  * List java: namespace `/resources/pings/jndi/java:`
  * List java:comp/env namespace `/resources/pings/jndi/java:comp/env`
  * List jave:global namespace `/resources/pings/jndi/java:global`
  * Start boot time `/resources/health/start-time`
  * Uptime in milliseconds `/resources/health/uptime`
  * Current memory stats `/resources/health/current-memory`
  * Operating system stats `/resources/health/os-info`

## Installation:

Drop the WAR https://github.com/AdamBien/ping/releases/ into your autodeploy folder and access the application with http://localhost:[YOUR_PORT]/ping

## Build:

Ping can be built with Maven like this:

    mvn clean package

Your WAR file will be written to the following directory:

    target/ping.war
