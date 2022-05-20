package com.example.demo.message;

import java.util.Objects;

/**
 * The greeing message.
 *
 * @since 1.1.0
 */
public class Message
{
    private String to;
    private String title;
    private String content;
    private String pictureUrl;

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

    public String getPictureUrl()
    {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(to, message.to) && Objects.equals(title, message.title) && Objects.equals(content, message.content) && Objects.equals(pictureUrl, message.pictureUrl);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(to, title, content, pictureUrl);
    }

    @Override
    public String toString()
    {
        return "Message{" +
            "to='" + to + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", pictureUrl='" + pictureUrl + '\'' +
            '}';
    }
}
