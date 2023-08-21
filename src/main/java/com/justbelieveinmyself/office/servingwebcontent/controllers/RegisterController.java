package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import com.justbelieveinmyself.office.servingwebcontent.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @GetMapping("/registration")
    public String register(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registerNewUser(User user, Map<String, Object> model){
        if(!jpaUserDetailsService.addUser(user)){
            model.put("message", "User with that name already exists!");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = jpaUserDetailsService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated.");
        }else{
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
