package com.example.demo.message;

import com.example.demo.repository.model.Member;

/**
 * A simple greeting message creator.
 *
 * @since 1.1.0
 */
public class SimpleMessageCreator implements MessageCreator
{
    @Override
    public Message create(Member member)
    {
        if(null == member)
        {
            throw new IllegalArgumentException("Member is null.");
        }

        if(member.getEmail() == null) // Check non-null for simplification.
        {
            throw new IllegalArgumentException("Email format error.");
        }

        if(member.getFirstName() == null) // Check non-null for simplification.
        {
            throw new IllegalArgumentException("No first name.");
        }

        Message msg = new Message();
        msg.setTo(member.getEmail());
        msg.setTitle("Subject: Happy birthday!");
        msg.setContent("Happy birthday, dear " + member.getFirstName() + "!");
        return msg;
    }
}
