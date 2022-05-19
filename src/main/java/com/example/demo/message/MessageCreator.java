package com.example.demo.message;

import com.example.demo.repository.model.Member;

/**
 * Greeting message creator.
 *
 * @since 1.1.0
 */
public interface MessageCreator
{
    /**
     * Create greeting message by member.
     *
     * @param member the member to greeting
     * @return the greeting message
     *
     * @throws IllegalArgumentException if message can't be created due to incorrect member data or null
     */
    Message create(Member member);
}
