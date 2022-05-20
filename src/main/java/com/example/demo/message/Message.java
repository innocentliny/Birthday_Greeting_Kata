package com.example.demo.message;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The greeing message.
 *
 * @since 1.1.0
 */
@XmlRootElement(name = "message")
public class Message
{
    private String to;
    private String title;
    private String content;

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(to, message.to) && Objects.equals(title, message.title) && Objects.equals(content, message.content);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(to, title, content);
    }

    @Override
    public String toString()
    {
        return "Message{" +
            "to='" + to + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
