package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class MainController {
//    @Autowired
//    private UserRepository userRepository;

//    @PostMapping ("/main") // Map ONLY POST Requests
//    public String addNewUser(@RequestParam String name, @RequestParam String message, Map<String, Object> model){
//        Iterable<User> users = userRepository.findAll();
//        if(!UserUtil.isUserExists(users, name)){
//            User user = new User(name, message, LocalDateTime.now());
//            userRepository.save(user);
//        }
//        users = userRepository.findAll();
//        model.put("users", users);
//        return "main";
//    }
//    @GetMapping("/main")
//    public String main(Map<String, Object> model){
//        Iterable<User> users = userRepository.findAll();
//        model.put("users", users);
//        return "main";
//    }
//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model){
////        Iterable<User> users = userRepository.findByMessage(filter);
//        Iterable<User> users;
//        users = filter != null && !filter.isEmpty()? userRepository.findByName(filter) : userRepository.findAll();
//        model.put("users", users);
//        return "main";
//    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping
    public String home(){
        return "home";
    }

}
