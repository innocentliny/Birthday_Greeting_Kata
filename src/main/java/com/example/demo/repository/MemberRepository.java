package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.model.Member;

public interface MemberRepository
{
    /**
     * Get members born today.
     * @return unmodified list or empty list if not found.
     */
    List<Member> getMembersBornToday();
}
