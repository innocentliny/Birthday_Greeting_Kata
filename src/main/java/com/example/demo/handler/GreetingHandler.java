package com.example.demo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.model.Member;
import com.example.demo.response.GreetingResponse;

public class GreetingHandler implements Handler
{
    private static final Logger log = LoggerFactory.getLogger(GreetingHandler.class); // It's ok to use "log" instead of "LOG" for me.

    private final MemberRepository repository;

    public GreetingHandler(MemberRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Response response()
    {
        List<Member> members = this.repository.getMembersBornToday();
        log.info("Got members born today: {}", members);

        if(Objects.isNull(members) || members.isEmpty())
        {
            log.info("No member born today found.");
            return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
        }

        List<GreetingResponse> greetingResponses = toGreetingResponse(members);
        log.info("Response: {}", greetingResponses);
        return Response.ok(greetingResponses).build();
    }

    private List<GreetingResponse> toGreetingResponse(List<Member> members)
    {
        List<GreetingResponse> greetingResponses = new ArrayList<>(members.size());
        for(Member member : members)
        {
            greetingResponses.add(GreetingResponse.builder()
                                                  .firstName(member.getFirstName())
                                                  .build());
        }
        return greetingResponses;
    }
}
