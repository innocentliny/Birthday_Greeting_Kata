package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Member;
import com.example.demo.repository.model.Member.Gender;
import com.example.demo.repository.model.Promotion;

public interface Repository
{
    /**
     * Get members to greet.
     * @return unmodified list or empty list if not found.
     *
     * @since v1.0.0
     */
    List<Member> getMembersToGreet();

    /**
     * Get promotion data by gender.
     * @param gender the member gender
     * @return the promotion data, return {@link Optional#empty()} if no promotion
     *
     * @see Optional
     */
    Optional<Promotion> getPromotion(Gender gender);

    /**
     * Get greeting picture.
     * @return the URL of picture, return {@link Optional#empty()} if no picture
     *
     * @see Optional
     */
    Optional<String> getPictureUrl();
}
