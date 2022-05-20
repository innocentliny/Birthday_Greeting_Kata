package com.example.demo;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.demo.handler.GreetingHandler;
import com.example.demo.message.MessageCreator;
import com.example.demo.message.MessageCreatorImpl;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.Repository;

@Singleton
@Path("/")
public class GreetingResource
{
    // We can use dependency injection framework to help.
    private MessageCreator msgCreator = new MessageCreatorImpl();

    // We can use dependency injection framework to help.
    private Repository repository = new FileRepository();

    @GET
    @Path("greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public Response greeting()
    {
        return new GreetingHandler(msgCreator, repository).response();
    }
}