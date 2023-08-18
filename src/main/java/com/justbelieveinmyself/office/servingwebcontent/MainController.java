package com.justbelieveinmyself.office.servingwebcontent;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.UserRepository;
import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping // Map ONLY POST Requests
    public String addNewUser(@RequestParam String name, @RequestParam String message, Map<String, Object> model){
        Iterable<User> users = userRepository.findAll();
        if(!UserUtil.isUserExists(users, name)){
            User user = new User(name, message, LocalDateTime.now());
            userRepository.save(user);
        }
        users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
//        Iterable<User> users = userRepository.findByMessage(filter);
        Iterable<User> users;
        users = filter != null && !filter.isEmpty()? userRepository.findByName(filter) : userRepository.findAll();
        model.put("users", users);
        return "main";
    }
    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }
}
