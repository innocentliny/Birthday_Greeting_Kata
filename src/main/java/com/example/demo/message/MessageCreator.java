package com.example.demo.message;

import java.util.Optional;

import com.example.demo.repository.model.Member;
import com.example.demo.repository.model.Promotion;

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
     * @param promotion the optional promotion data. Use {@link Optional#empty()} if no promotion
     * @return the greeting message
     *
     * @throws IllegalArgumentException if message can't be created due to incorrect member data or null
     * @since 2.0.0
     *
     * @see Optional
     */
    Message create(Member member, Optional<Promotion> promotion);
}
