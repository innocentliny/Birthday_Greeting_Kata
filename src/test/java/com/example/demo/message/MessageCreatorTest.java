package com.example.demo.message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.demo.repository.model.Member;
import com.example.demo.repository.model.Member.MemberBuilder;
import com.example.demo.repository.model.Promotion;

public class MessageCreatorTest
{
    private MessageCreator msgCreator = new MessageCreatorImpl();
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
        msgCreator.create(null, Optional.empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoEmailThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.email(null).build(), Optional.empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidEmailThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.email("ooxx@").build(), Optional.empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoFirstNameThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.firstName(null).build(), Optional.empty());
    }

    @Test
    public void testMessageWithoutPromotion()
    {
        // Arrange
        Member member = memberBuilder.build();

        // Action
        Message msg = msgCreator.create(member, Optional.empty());

        // Assert
        Assert.assertEquals(member.getEmail(), msg.getTo());
        Assert.assertNotNull(msg.getTitle());
        Assert.assertNotNull(msg.getContent());
        Assert.assertTrue(msg.getContent().contains(member.getFirstName()));
    }

    @Test
    public void testMessageWithPromotion()
    {
        // Arrange
        Member member = memberBuilder.build();
        final int discountRate = 20;
        final List<String> items = Arrays.asList("A", "B");
        Promotion promotion = new Promotion(discountRate, items);

        // Action
        Message msg = msgCreator.create(member, Optional.of(promotion));

        // Assert
        Assert.assertEquals(member.getEmail(), msg.getTo());
        Assert.assertNotNull(msg.getTitle());
        Assert.assertNotNull(msg.getContent());
        Assert.assertTrue(msg.getContent().contains(member.getFirstName()));
        Assert.assertTrue(msg.getContent().contains(String.valueOf(discountRate)));
        Assert.assertTrue(msg.getContent().contains(items.get(0)));
        Assert.assertTrue(msg.getContent().contains(items.get(1)));
    }
}
