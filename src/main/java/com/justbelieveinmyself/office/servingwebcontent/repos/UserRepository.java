package com.justbelieveinmyself.office.servingwebcontent.repos;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByActivationCode(String code);
}
