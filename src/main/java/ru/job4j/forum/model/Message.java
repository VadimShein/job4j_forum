package ru.job4j.forum.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created = new GregorianCalendar();

    @ManyToOne
    private Topic topic;

    public Message() {
    }

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Message(String author, String text, Calendar created) {
        this.author = author;
        this.text = text;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String username) {
        this.author = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{"
                + "id=" + id
                + ", author='" + author + '\''
                + ", text='" + text + '\''
                + ", created=" + created
                + ", topic=" + topic + '}';
    }
}
