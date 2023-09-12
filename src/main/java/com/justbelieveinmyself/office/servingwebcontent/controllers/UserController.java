package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.domain.Role;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    JpaUserDetailsService userDetailsService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model){
        model.addAttribute("users", userDetailsService.findAll());
        return "userList";
    }
    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(@RequestParam String username, @RequestParam Map<String, String> form,@RequestParam("userId") User user){
        userDetailsService.saveUser(user, username, form);
        return "redirect:/user";
    }
    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }
    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String password, @RequestParam String email){
        userDetailsService.updateProfile(user, password, email);
        return "redirect:/user/profile";
    }
    @GetMapping("/subscribe/{user}")
    public String subscribeToUser(@AuthenticationPrincipal User currentUser
            , @PathVariable User user){
        userDetailsService.subscribe(currentUser, user);

        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("/unsubscribe/{user}")
    public String unsubscribeUser(@AuthenticationPrincipal User currentUser
            , @PathVariable User user){
        userDetailsService.unsubscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("{type}/{user}/list")
    public String subList(Model model
            , @PathVariable User user
            , @PathVariable String type){
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);
        if("subscriptions".equals(type)){
            model.addAttribute("users", user.getSubscriptions());
        }else{
            model.addAttribute("users", user.getSubscribers());
        }
        return "subscriptions";
    }
}
