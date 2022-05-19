package com.example.demo.message;

import com.example.demo.repository.model.Member;

/**
 * Create greeting message by member.
 *
 * @since 1.1.0
 */
public interface MessageCreator
{
    Message create(Member member);
}
