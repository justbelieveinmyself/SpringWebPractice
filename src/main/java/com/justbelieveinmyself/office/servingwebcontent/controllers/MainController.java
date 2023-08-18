package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.Message;
import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.SecurityUser;
import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.MessageRepository;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal SecurityUser user, @RequestParam String text
            , @RequestParam String tag, Map<String, Object> model){
        Message message = new Message();
        message.setText(text);
        message.setTag(tag);
        message.setAuthor(user.getUser());
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
//    @PostMapping("/search")
//    public String searchMessageByUsername(@RequestParam String filter, Map<String, Object> model){
//        User user = new User();
//        messageRepository.findByAuthor()
//        model.put("messages", )
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
