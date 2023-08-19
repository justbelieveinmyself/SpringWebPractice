package com.justbelieveinmyself.office.servingwebcontent.repos;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.Message;
import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByAuthor(User author);
    List<Message> findByTag(String tag);
}
