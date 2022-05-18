package com.example.demo.response;

import java.util.Objects;

public class GreetingResponse
{
    private String title;
    private String content;

    private GreetingResponse()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        GreetingResponse that = (GreetingResponse) o;
        return Objects.equals(title, that.title) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(title, content);
    }

    @Override
    public String toString()
    {
        return "GreetingResponse{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
    }

    public static GreetingResponseBuilder builder()
    {
        return new GreetingResponseBuilder();
    }
    public static final class GreetingResponseBuilder
    {
        private String title = "Subject: Happy birthday!";
        private String firstName;

        private GreetingResponseBuilder()
        {
        }

        public GreetingResponseBuilder title(String title)
        {
            this.title = title;
            return this;
        }

        public GreetingResponseBuilder firstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public GreetingResponse build()
        {
            GreetingResponse greetingResponse = new GreetingResponse();
            greetingResponse.content = "Happy birthday, dear " + this.firstName + "!";
            greetingResponse.title = this.title;
            return greetingResponse;
        }
    }
}
