package com.justbelieveinmyself.office.servingwebcontent.domain.util;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class MessageHelper {
    public static String getAuthorName(User author){
        return author != null ? author.getUsername() : "<none>";
    }
    public static String getFormattedTime(LocalDateTime time){
        return time == null?"none":time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}
