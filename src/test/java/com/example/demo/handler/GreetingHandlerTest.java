package com.example.demo.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.example.demo.message.Message;
import com.example.demo.message.MessageCreator;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.model.Member;

public class GreetingHandlerTest
{
    @Test
    public void testGreetingMessages()
    {
        // Arrange
        List<Member> members = new ArrayList<>(2);
        members.add(Member.builder()
                          .firstName("Robert")
                          .lastName("Yen")
                          .gender(Member.Gender.MALE)
                          .birthday("1985/8/8")
                          .email("robert.yen@linecorp.com")
                          .build());
        members.add(Member.builder()
                          .firstName("Sherry")
                          .lastName("Chen")
                          .gender(Member.Gender.FEMALE)
                          .birthday("1993/8/8")
                          .email("sherry.lai@linecorp.com")
                          .build());
        MemberRepository repo = new MemberRepository() {
            @Override
            public List<Member> getMembersBornToday()
            {
                return members;
            }
        };

        MessageCreator msgCreator = new MessageCreator()
        {
            @Override
            public Message create(Member member)
            {
                Message msg = new Message();
                msg.setTo(member.getEmail());
                msg.setTitle("Subject: Happy birthday!");
                msg.setContent("Happy birthday, dear " + member.getFirstName() + "!");
                return msg;
            }
        };

        // Action
        Response rsp = new GreetingHandler(msgCreator, repo).response();

        // Assert
        Assert.assertEquals(Status.OK.getStatusCode(), rsp.getStatus());
        Assert.assertTrue(rsp.hasEntity());
        Assert.assertTrue(rsp.getEntity() instanceof List);
        List<Message> messages = (List<Message>) rsp.getEntity();
        Assert.assertEquals(2, messages.size());
        for(Message msg : messages)
        {
            Assert.assertNotNull(msg.getTo());
            Assert.assertNotNull(msg.getTitle());
            Assert.assertNotNull(msg.getContent());
        }
    }

    @Test
    public void testMembersNotFound()
    {
        // Arrange
        MemberRepository repo = new MemberRepository()
        {
            @Override
            public List<Member> getMembersBornToday()
            {
                return Collections.emptyList();
            }
        };

        MessageCreator msgCreator = new MessageCreator()
        {
            @Override
            public Message create(Member member)
            {
                return null;
            }
        };

        // Action
        Response rsp = new GreetingHandler(msgCreator, repo).response();

        // Assert
        Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), rsp.getStatus());
        Assert.assertFalse(rsp.hasEntity());
    }
}
