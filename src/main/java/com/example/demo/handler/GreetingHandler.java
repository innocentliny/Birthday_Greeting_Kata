package com.example.demo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.message.Message;
import com.example.demo.message.MessageCreator;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.model.Member;

public class GreetingHandler implements Handler
{
    private static final Logger log = LoggerFactory.getLogger(GreetingHandler.class); // It's ok to use "log" instead of "LOG" for me.

    private final MessageCreator msgCreator;
    private final MemberRepository repository;

    public GreetingHandler(MessageCreator msgCreator, MemberRepository repository)
    {
        this.msgCreator = msgCreator;
        this.repository = repository;
    }

    @Override
    public Response response()
    {
        List<Member> members = this.repository.getMembersToGreet();
        log.info("Got members born today: {}", members);

        if(Objects.isNull(members) || members.isEmpty())
        {
            log.info("No member born today found.");
            return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
        }

        List<Message> messages = createMessages(members);
        log.info("Response: {}", messages);
        return Response.ok(messages).build();
    }

    private List<Message> createMessages(List<Member> members)
    {
        List<Message> messages = new ArrayList<>(members.size());

        for(Member member : members)
        {
            try
            {
                messages.add(msgCreator.create(member));
            }
            catch(IllegalArgumentException e)
            {
                log.warn("Can't create message from member({}): {}", member, e.getMessage());
            }
        }

        return messages;
    }
}
