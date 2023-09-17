package com.justbelieveinmyself.office.servingwebcontent.services;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Page<Message> getMessageList(String filter, Pageable pageable) {
        return (filter != null && !filter.isEmpty())? messageRepository.findByTag(filter, pageable) : messageRepository.findAll(pageable);
    }

    public Page<Message> findByAuthor(User user, Pageable pageable) {
        return messageRepository.findByAuthor(user, pageable);
    }

    public void save(Message message) {
        messageRepository.save(message);
    }
}
