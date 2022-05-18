package com.example.demo.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.model.Member;
import com.example.demo.response.GreetingResponse;

public class GreetingHandlerTest
{
    @Test
    public void testGreetingMembers()
    {
        // Arrange
        List<Member> members = new ArrayList<>(2);
        members.add(Member.builder()
                          .firstName("Robert")
                          .lastName("Yen")
                          .gender(Member.Gender.Male)
                          .birthday("1985/8/8")
                          .email("robert.yen@linecorp.com")
                          .build());
        members.add(Member.builder()
                          .firstName("Sherry")
                          .lastName("Chen")
                          .gender(Member.Gender.Female)
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

        // Action
        Response rsp = new GreetingHandler(repo).response();

        // Assert
        Assert.assertEquals(rsp.getStatus(), Status.OK.getStatusCode());
        Assert.assertTrue(rsp.hasEntity());
        Assert.assertTrue(rsp.getEntity() instanceof List);
        List<GreetingResponse> greetingResponses = (List<GreetingResponse>) rsp.getEntity();
        Assert.assertEquals(greetingResponses.size(), 2);
        for(GreetingResponse greetingResponse : greetingResponses)
        {
            Assert.assertNotNull(greetingResponse.getTitle());
            Assert.assertNotNull(greetingResponse.getContent());
        }
    }

    @Test
    public void testGreetingMembersButNotFound()
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

        // Action
        Response rsp = new GreetingHandler(repo).response();

        // Assert
        Assert.assertEquals(rsp.getStatus(), Status.NOT_FOUND.getStatusCode());
        Assert.assertFalse(rsp.hasEntity());
    }
}
