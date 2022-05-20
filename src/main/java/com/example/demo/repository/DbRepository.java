package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Member;
import com.example.demo.repository.model.Member.Gender;
import com.example.demo.repository.model.Promotion;

public class DbRepository implements Repository
{
    @Override
    public List<Member> getMembersToGreet()
    {
        // Will get members whose age > 49.
        List<Member> members = new ArrayList<>(2);
        members.add(Member.builder()
                          .firstName("Peter")
                          .lastName("Wang")
                          .gender(Member.Gender.MALE)
                          .birthday("1950/12/22 ")
                          .email("peter.wang@linecorp.com")
                          .build());
        return Collections.unmodifiableList(members); // Golden source should be unmodified by anyone.
    }

    @Override
    public Optional<Promotion> getPromotion(Gender gender)
    {
        return Optional.empty();
    }

    @Override
    public Optional<String> getPictureUrl()
    {
        return Optional.of("https://cdn.example.com/pic/elderGreeting.gif");
    }
}
