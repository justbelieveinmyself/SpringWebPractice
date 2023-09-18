package com.justbelieveinmyself.office.servingwebcontent.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String text;
    @Length(max = 255, message = "Tag too long")
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String filename;
    private LocalDateTime time;
    public Message(){}

    public Message(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public LocalDateTime getTime() {
        return time;
    }
//    public String getTimeFromLastUpdate(){
//        ZoneId zoneId = ZoneId.systemDefault();
//        Long timeBetween = LocalDateTime.now().atZone(zoneId).toEpochSecond() - getTime().atZone(zoneId).toEpochSecond();
//        LocalDateTime.ofEpochSecond(timeBetween, timeBetween, zoneId);
//
//    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public String getFormattedTime(){
        return getTime() == null?"none":getTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
