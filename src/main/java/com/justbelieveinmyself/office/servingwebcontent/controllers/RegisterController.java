package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.User;
import com.justbelieveinmyself.office.servingwebcontent.service.JpaUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Objects;

@Controller
public class RegisterController {
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @GetMapping("/registration")
    public String register(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model){
        if(!Objects.equals(user.getPassword(), user.getPassword2())){
            model.addAttribute("passwordError", "Passwords are different!");
            return "registration";
        }
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registration";
        }
        if(!jpaUserDetailsService.addUser(user)){
            model.addAttribute("usernameError", "User with that name already exists!");
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
