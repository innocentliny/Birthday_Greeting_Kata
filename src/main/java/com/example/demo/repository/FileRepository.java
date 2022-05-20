package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.demo.repository.model.Member;

/**
 * Member data from file.
 */
public class FileRepository implements Repository
{
    @Override
    public List<Member> getMembersToGreet()
    {
        // Will get members born today.
        List<Member> members = new ArrayList<>(2);
        members.add(Member.builder()
                          .firstName("Robert")
                          .lastName("Yen")
                          .gender(Member.Gender.MALE)
                          .birthday("1985/8/8")
                          .email("robert.yen@linecorp.com")
                          .build());
        members.add(Member.builder()
                          .firstName("Sherry")
                          .lastName("Chen")
                          .gender(Member.Gender.FEMALE)
                          .birthday("1993/8/8")
                          .email("sherry.lai@linecorp.com")
                          .build());
        return Collections.unmodifiableList(members); // Golden source should be unmodified by anyone.
    }
}