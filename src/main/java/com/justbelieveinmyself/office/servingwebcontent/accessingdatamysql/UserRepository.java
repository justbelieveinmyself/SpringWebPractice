package com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByMessage(String message);
    List<User> findByName(String name);
}
