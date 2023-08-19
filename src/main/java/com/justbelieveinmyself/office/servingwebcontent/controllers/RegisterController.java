package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.Role;
import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/registration")
    public String register(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registerNewUser(@RequestParam String username, @RequestParam String password, Map<String, Object> model){
        Optional<User> users = userRepository.findByName(username);
        if(users.isPresent()){
            model.put("message", "User with that name already exists!");
            return "registration";
        }
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setActive(true);
        user.setTime(LocalDateTime.now());
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/login";
    }
}
