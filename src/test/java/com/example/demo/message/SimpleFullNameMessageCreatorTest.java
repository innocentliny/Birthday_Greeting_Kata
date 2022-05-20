package com.example.demo.message;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.demo.repository.model.Member;
import com.example.demo.repository.model.Member.MemberBuilder;

public class SimpleFullNameMessageCreatorTest
{
    private MessageCreator msgCreator = new SimpleFullNameMessageCreator();
    private MemberBuilder memberBuilder = Member.builder();

    @BeforeMethod
    private void beforeMethod()
    {
        memberBuilder.email("email@fake.com")
                     .gender(Member.Gender.FEMALE)
                     .lastName("myLastName")
                     .firstName("myFirstName")
                     .birthday("1976/01/01");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNullMemberThenIllegalArgumentException()
    {
        msgCreator.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoEmailThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.email(null).build());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidEmailThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.email("ooxx@").build());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoFirstNameThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.firstName(null).build());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoLastNameThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.lastName(null).build());
    }

    @Test
    public void testMemberWithCorrectData()
    {
        // Arrange
        Member member = memberBuilder.build();

        // Action
        Message msg = msgCreator.create(member);

        // Assert
        Assert.assertEquals(member.getEmail(), msg.getTo());
        Assert.assertNotNull(msg.getTitle());
        Assert.assertNotNull(msg.getContent());
        Assert.assertTrue(msg.getContent().contains(member.getFirstName()));
        Assert.assertTrue(msg.getContent().contains(member.getLastName()));
    }
}
