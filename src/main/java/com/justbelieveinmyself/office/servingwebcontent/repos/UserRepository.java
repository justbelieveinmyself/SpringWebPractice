package com.justbelieveinmyself.office.servingwebcontent.repos;

import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
