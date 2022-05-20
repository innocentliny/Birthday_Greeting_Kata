package com.example.demo.message;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.repository.model.Member;
import com.example.demo.repository.model.Promotion;
import com.sanctionco.jmail.JMail;

/**
 * A simple greeting message creator.
 *
 * @since 1.1.0
 */
public class MessageCreatorImpl implements MessageCreator
{
    @Override
    public Message create(Member member, Optional<Promotion> promotion)
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
        msg.setTitle("Subject: Happy birthday!"); // No i18n support for simplification.

        StringBuilder contentBuilder = new StringBuilder("Happy birthday, dear " + member.getFirstName() + "!");
        if(Objects.nonNull(promotion) && promotion.isPresent())
        {
            contentBuilder.append('\n')
                          .append("We offer special discount ")
                          .append(promotion.get().getDiscountRate())
                          .append("% off for the following items:")
                          .append('\n')
                          .append(String.join(", ", promotion.get().getItems()));
        }
        msg.setContent(contentBuilder.toString());
        return msg;
    }
}
