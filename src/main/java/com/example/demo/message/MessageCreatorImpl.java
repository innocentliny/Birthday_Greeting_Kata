package com.example.demo.message;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.repository.model.Member;
import com.sanctionco.jmail.JMail;

/**
 * A simple greeting message creator.
 *
 * @since 1.1.0
 */
public class MessageCreatorImpl implements MessageCreator
{
    @Override
    public Message create(Member member)
    {
        if(Objects.isNull(member))
        {
            throw new IllegalArgumentException("Member is null.");
        }

        if(JMail.strictValidator().isInvalid(member.getEmail()))
        {
            throw new IllegalArgumentException("Email format error.");
        }

        if(StringUtils.isBlank(member.getFirstName()))
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
