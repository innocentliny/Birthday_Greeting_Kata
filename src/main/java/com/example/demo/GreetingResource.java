package com.example.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class GreetingResource
{
    private static final Logger log = LoggerFactory.getLogger(GreetingResource.class); // It's ok to use "log" instead of "LOG" for me.

    @GET
    @Path("/hello-world")
    @Produces("text/plain")
    public String hello()
    {
        log.info("xxxx");
        return "Hello, World!";
    }
}