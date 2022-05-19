package com.example.demo.repository.model;

import java.util.Objects;

/**
 * A read-only member model from repository.
 *
 * @see #builder()
 * @see MemberBuilder
 */
public class Member
{
    public enum Gender
    {
        MALE,
        FEMALE
    }
    private String firstName;
    private String lastName;
    private Gender gender;
    private String birthday;
    private String email;

    private Member()
    {
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public Gender getGender()
    {
        return gender;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(firstName, member.firstName) &&
               Objects.equals(lastName, member.lastName) &&
               gender == member.gender &&
               Objects.equals(birthday, member.birthday) &&
               Objects.equals(email, member.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(firstName, lastName, gender, birthday, email);
    }

    @Override
    public String toString()
    {
        return "Member{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", gender=" + gender +
            ", birthday=" + birthday +
            ", email='" + email + '\'' +
            '}';
    }

    public static MemberBuilder builder()
    {
        return new MemberBuilder();
    }

    public static final class MemberBuilder
    {
        private String firstName;
        private String lastName;
        private Gender gender;
        private String birthday;
        private String email;

        private MemberBuilder()
        {
        }

        public MemberBuilder firstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public MemberBuilder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }

        public MemberBuilder gender(Gender gender)
        {
            this.gender = gender;
            return this;
        }

        public MemberBuilder birthday(String birthday)
        {
            this.birthday = birthday;
            return this;
        }

        public MemberBuilder email(String email)
        {
            this.email = email;
            return this;
        }

        public Member build()
        {
            Member member = new Member();
            member.email = this.email;
            member.firstName = this.firstName;
            member.lastName = this.lastName;
            member.gender = this.gender;
            member.birthday = this.birthday;
            return member;
        }
    }
}
