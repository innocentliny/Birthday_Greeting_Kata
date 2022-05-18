package com.example.demo;

import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.handler.GreetingHandler;
import com.example.demo.repository.MemberDbRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.response.GreetingResponse;

@Singleton
@Path("/")
public class GreetingResource
{
    @GET
    @Path("greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public Response greeting()
    {
        return new GreetingHandler(MemberDbRepository.getInstance()).response();
    }
}