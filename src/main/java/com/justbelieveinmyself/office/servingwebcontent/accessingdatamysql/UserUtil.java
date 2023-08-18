package com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql;

import java.util.Objects;

public class UserUtil {
    public static boolean isUserExists(Iterable<User> users, String name){
        for (User u : users) {
            if(Objects.equals(u.getName(), name)){
                return true;
            }
        }
        return false;
    }
}
