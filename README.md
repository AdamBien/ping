# A 9 kB WAR Ping For JavaEE 7 Application Servers

Ping exposes basic health, JNDI, JVM and OS metrics via a convenient URI, which enables an easy integration into application monitoring solutions.

Tested with GlassFish, Payara, TomEE, WebSphere Liberty and Wildfly.

## Installation:

Drop the WAR https://github.com/AdamBien/ping/releases/ into your autodeploy folder and access the application with http://localhost:[YOUR_PORT]/ping

## Build:

Ping can be built with Maven like this:

    mvn clean package
