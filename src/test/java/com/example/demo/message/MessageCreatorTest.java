package com.example.demo.message;

import java.util.Arrays;
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
        msgCreator.create(null, Optional.empty(), Optional.empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoEmailThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.email(null).build(), Optional.empty(), Optional.empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidEmailThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.email("ooxx@").build(), Optional.empty(), Optional.empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNoFirstNameThenIllegalArgumentException()
    {
        msgCreator.create(memberBuilder.firstName(null).build(), Optional.empty(), Optional.empty());
    }

    @Test
    public void testMessageWithMemberOnly()
    {
        // Arrange
        Member member = memberBuilder.build();

        // Action
        Message msg = msgCreator.create(member, Optional.empty(), Optional.empty());

        // Assert
        Assert.assertEquals(member.getEmail(), msg.getTo());
        Assert.assertNotNull(msg.getTitle());
        Assert.assertNotNull(msg.getContent());
        Assert.assertTrue(msg.getContent().contains("`" + member.getFirstName() + "`"));
        Assert.assertFalse(msg.getContent().contains(member.getLastName()));
        Assert.assertNull(msg.getPictureUrl());
    }

    @Test
    public void testMessageWithPromotionButNoPictureUrl()
    {
        // Arrange
        Member member = memberBuilder.build();
        Promotion promotion = new Promotion(20, Arrays.asList("A", "B"));

        // Action
        Message msg = msgCreator.create(member, Optional.of(promotion), Optional.empty());

        // Assert
        Assert.assertEquals(member.getEmail(), msg.getTo());
        Assert.assertNotNull(msg.getTitle());
        Assert.assertNotNull(msg.getContent());
        Assert.assertTrue(msg.getContent().contains("`" + member.getFirstName() + "`"));
        Assert.assertTrue(msg.getContent().contains(String.valueOf(promotion.getDiscountRate())));
        for(String item : promotion.getItems())
        {
            Assert.assertTrue(msg.getContent().contains(item));
        }
        Assert.assertNull(msg.getPictureUrl());
    }

    @Test
    public void testMessageWithPromotionAndPictureUrl()
    {
        // Arrange
        Member member = memberBuilder.build();
        Promotion promotion = new Promotion(50, Arrays.asList("C"));
        String pictureUrl = "abcdefg";

        // Action
        Message msg = msgCreator.create(member, Optional.of(promotion), Optional.of(pictureUrl));

        // Assert
        Assert.assertEquals(member.getEmail(), msg.getTo());
        Assert.assertNotNull(msg.getTitle());
        Assert.assertNotNull(msg.getContent());
        Assert.assertTrue(msg.getContent().contains("`" + member.getFirstName() + "`"));
        Assert.assertTrue(msg.getContent().contains(String.valueOf(promotion.getDiscountRate())));
        for(String item : promotion.getItems())
        {
            Assert.assertTrue(msg.getContent().contains(item));
        }
        Assert.assertEquals(pictureUrl, msg.getPictureUrl());
    }
}
