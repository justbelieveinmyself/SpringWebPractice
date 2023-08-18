package com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;
    @Override
    public String getAuthority() {
        return name();
    }

}
