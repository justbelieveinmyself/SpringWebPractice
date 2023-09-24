package com.justbelieveinmyself.office.servingwebcontent.services;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto;
import com.justbelieveinmyself.office.servingwebcontent.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public Page<MessageDto> getMessageList(User currentUser, String filter, Pageable pageable) {
        return (filter != null && !filter.isEmpty())? messageRepository.findByTag(currentUser, filter, pageable) : messageRepository.findAll(currentUser, pageable);
    }

    public Page<MessageDto> findByAuthor(User currentUser, User author, Pageable pageable) {
        return messageRepository.findByAuthor(currentUser, author, pageable);
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
