package com.justbelieveinmyself.office.servingwebcontent.excepitons;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundInDataBase extends AuthenticationException {

    public UserNotFoundInDataBase(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNotFoundInDataBase(String msg) {
        super(msg);
    }
}
