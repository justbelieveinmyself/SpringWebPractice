package com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String message;
//    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm:ss") //not working
    private LocalDateTime time;
    public User(String name, String message, LocalDateTime time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }
    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
